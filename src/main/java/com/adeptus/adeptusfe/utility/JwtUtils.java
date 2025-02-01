package com.adeptus.adeptusfe.utility;

import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

public class JwtUtils {
    public static String getStaffIdFromJWT(String token) throws Exception {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

    public static String getFullNameFromJWT(String token) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
        return claims.getStringClaim("fullname");
    }
    public static boolean isTokenExpired(String token) {
        if (token == null || token.isEmpty()) {
            return true; // Token trống hoặc null, giả sử là đã hết hạn
        }
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date expirationDate = signedJWT.getJWTClaimsSet().getExpirationTime();
            return expirationDate.before(new Date());
        } catch (Exception e) {
            // Nếu token không hợp lệ hoặc có lỗi trong quá trình xử lý, coi như token hết hạn
            return true;
        }
    }
}
