package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.dto.response.RoleResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.util.List;

public class RoleService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ApiResponse<List<RoleResponse>> getAllRoles() throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "role/all");
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(
                jsonResponse,
                new TypeReference<>() {
                }
        );
    }


}