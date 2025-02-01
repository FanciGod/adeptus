package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.request.AuthenticationRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.AuthenticationResponse;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.IOException;

public class StaffService {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public ApiResponse<PageResponse<StaffResponse>> getStaffList(int page, int size) throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "staff/all?page=" + page + "&size=" + size);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(
                jsonResponse, objectMapper.getTypeFactory().constructParametricType(
                        ApiResponse.class,
                        objectMapper.getTypeFactory().constructParametricType(PageResponse.class, StaffResponse.class)
                )
        );
    }
}
