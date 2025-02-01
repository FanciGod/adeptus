package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.request.AuthenticationRequest;
import com.adeptus.adeptusfe.dto.request.CreateNewStaffRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.AuthenticationResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.IOException;

public class NewStaffService {
    private final ObjectMapper objectMapper;

    public NewStaffService() {
        // Khởi tạo ObjectMapper với module JavaTimeModule
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public ApiResponse<StaffResponse> createNewStaff(CreateNewStaffRequest request) throws IOException {

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("username", request.getUsername())
                .addFormDataPart("password", request.getPassword())
                .addFormDataPart("rePassword", request.getRePassword())
                .addFormDataPart("fullName", request.getFullName())
                .addFormDataPart("email", request.getEmail())
                .addFormDataPart("phone", request.getPhone())
                .addFormDataPart("dob", request.getDob().toString())
                .addFormDataPart("salary", request.getSalary().toString());
        for (Long roleId : request.getRoleId()) {
            builder.addFormDataPart("roleId", roleId.toString());  // Thêm mỗi roleId vào form-data
        }
        if (request.getThumbnail() != null) {
            builder.addFormDataPart("thumbnail", request.getThumbnail().getName(),
                    RequestBody.create(request.getThumbnail(), MediaType.parse("image/*")));
        }
        RequestBody body = builder.build();

        String jsonResponse = HttpClient.post(Environment.url + "staff/new", body);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }
}
