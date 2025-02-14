package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.request.CreateNewStaffRequest;
import com.adeptus.adeptusfe.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

public class StaffService {

    private final ObjectMapper objectMapper;

    public StaffService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }


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

    public ApiResponse<StaffResponse> getStaffById(Long id) throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "staff/info/" + id);
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
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

    public ApiResponse<StaffResponse> updateStaffBasicInfoById(UpdateStaffBasicInfoRequest request, Long id) throws IOException {
        String json = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(json, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.put(Environment.url + "staff/update/" + id, body);
        return objectMapper.readValue(jsonResponse,
                new TypeReference<>() {
                });
    }

    public void deleteStaffById(Long id) throws IOException {
        String jsonResponse = HttpClient.delete(Environment.url + "staff/delete/" + id);
    }
}
