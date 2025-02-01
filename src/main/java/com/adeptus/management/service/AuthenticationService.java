package com.adeptus.management.service;

import com.adeptus.management.dto.request.AuthenticationRequest;
import com.adeptus.management.dto.request.IntrospectRequest;
import com.adeptus.management.dto.request.LogoutRequest;
import com.adeptus.management.dto.request.RefreshTokenRequest;
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
