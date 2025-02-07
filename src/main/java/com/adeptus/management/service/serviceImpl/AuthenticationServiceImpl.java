package com.adeptus.management.service.serviceImpl;

import com.adeptus.management.dto.request.AuthenticationRequest;
import com.adeptus.management.dto.request.IntrospectRequest;
import com.adeptus.management.dto.request.LogoutRequest;
import com.adeptus.management.dto.request.RefreshTokenRequest;
import com.adeptus.management.dto.response.AuthenticationResponse;
import com.adeptus.management.dto.response.IntrospectResponse;
import com.adeptus.management.entity.auth.InvalidToken;
import com.adeptus.management.entity.staff.Staff;
import com.adeptus.management.exception.AppException;
import com.adeptus.management.exception.ErrorCode;
import com.adeptus.management.repository.InvalidTokenRepository;
import com.adeptus.management.repository.StaffRepository;
import com.adeptus.management.service.AuthenticationService;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    @Value("${jwt.signerKey}")
    private String singerKey;

    @Value("${jwt.valid-duration}")
    private int validDuration;

    @Value("${jwt.refreshable-duration}")
    private int refreshableDuration;

    private final StaffRepository staffRepository;
    private final PasswordEncoder passwordEncoder;
    private final InvalidTokenRepository invalidTokenRepository;

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) throws JOSEException {
        Staff staff = staffRepository.findActiveStaffByUsername(request.getUsername()).orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_FOUND));
        boolean isAuthenticated = passwordEncoder.matches(request.getPassword(), staff.getPassword());
        if (!isAuthenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        String token = generateToken(staff);
        return AuthenticationResponse.builder()
                .isAuthenticated(true)
                .token(token)
                .build();
    }

    @Override
    public void logout(LogoutRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(request.getToken(), true);
        InvalidToken invalidToken = InvalidToken.builder()
                .tokenId(signedJWT.getJWTClaimsSet().getJWTID())
                .expiryTime(signedJWT.getJWTClaimsSet().getExpirationTime())
                .build();
        invalidTokenRepository.save(invalidToken);
    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        String token = request.getToken();
        boolean isValid = true;
        try {
            verifyToken(token, false);
        } catch (Exception e) {
            isValid = false;
        }
        return IntrospectResponse.builder()
                .isValid(isValid)
                .build();
    }

    @Override
    public AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        SignedJWT signedJWT = verifyToken(request.getToken(), false);
        InvalidToken invalidToken = InvalidToken.builder()
                .tokenId(signedJWT.getJWTClaimsSet().getJWTID())
                .expiryTime(signedJWT.getJWTClaimsSet().getExpirationTime())
                .build();
        invalidTokenRepository.save(invalidToken);

        Staff staff = staffRepository.findById(Long.parseLong(signedJWT.getJWTClaimsSet().getSubject())).orElseThrow(() -> new AppException(ErrorCode.USER_ID_NOT_FOUND));
        String token = generateToken(staff);

        return AuthenticationResponse.builder()
                .token(token)
                .isAuthenticated(true)
                .build();
    }
    private String generateToken(Staff staff) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                .subject(staff.getId().toString())
                .issuer("adeptus")
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(Instant.now().plus(validDuration, ChronoUnit.SECONDS).toEpochMilli()))
                .claim("scope", buildScope(staff))
                .claim("fullname", staff.getFullName())
                .build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        JWSSigner jwsSigner = new MACSigner(singerKey.getBytes());
        jwsObject.sign(jwsSigner);
        return jwsObject.serialize();
    }

    private String buildScope(Staff staff) {
        StringJoiner stringJoiner = new StringJoiner(" ");
        if (staff.getRoles() != null) {
            staff.getRoles().forEach(role -> {
                stringJoiner.add("ROLE_" + role.getRoleName());
                role.getPermissions().forEach(permission -> stringJoiner.add(permission.getPermissionName()));

            });
        }
        return stringJoiner.toString();
    }

    private SignedJWT verifyToken(String token, boolean isRefresh) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(singerKey.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expiryTime;
        if (isRefresh) {
            expiryTime = new Date(signedJWT.getJWTClaimsSet().getExpirationTime().toInstant().plus(refreshableDuration, ChronoUnit.SECONDS).toEpochMilli());
        } else {
            expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        }
        boolean verified = signedJWT.verify(verifier);
        if (!verified && expiryTime.after(new Date())) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        if (invalidTokenRepository.findById(signedJWT.getJWTClaimsSet().getJWTID()).isPresent()) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        return signedJWT;

    }
}
