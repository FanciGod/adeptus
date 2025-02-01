package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.request.AuthenticationRequest;
import com.adeptus.adeptusfe.dto.request.LogoutRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.AuthenticationResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;

public class AuthenticationService {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponse<AuthenticationResponse> login(AuthenticationRequest request) throws IOException {
        String json = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.post(Environment.url + "auth/login", body);
        return objectMapper.readValue(jsonResponse,
                new TypeReference<>() {
                });
    }

    public void logout(LogoutRequest request) throws IOException {
        String json = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        HttpClient.post(Environment.url + "auth/logout", body);
    }
}
