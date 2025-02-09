package com.adeptus.management.service;

import com.adeptus.management.dto.request.auth.AuthenticationRequest;
import com.adeptus.management.dto.request.auth.IntrospectRequest;
import com.adeptus.management.dto.request.auth.LogoutRequest;
import com.adeptus.management.dto.request.auth.RefreshTokenRequest;
import com.adeptus.management.dto.response.AuthenticationResponse;
import com.adeptus.management.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    AuthenticationResponse login(AuthenticationRequest request) throws JOSEException;

    void logout(LogoutRequest request) throws ParseException, JOSEException;

    IntrospectResponse introspect(IntrospectRequest request);

    AuthenticationResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException;
}
