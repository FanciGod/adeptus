package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.request.CreateNewClassRequest;
import com.adeptus.adeptusfe.dto.request.UpdateClassRequest;
import com.adeptus.adeptusfe.dto.response.ApiResponse;
import com.adeptus.adeptusfe.dto.response.ClassesResponse;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;

public class ClassesService {
    private final ObjectMapper objectMapper;

    public ClassesService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public ApiResponse<PageResponse<ClassesResponse>> getClassWithPagination(int page, int size) throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "class/all?page=" + page + "&size=" + size);
        return objectMapper.readValue(
                jsonResponse, objectMapper.getTypeFactory().constructParametricType(
                        ApiResponse.class,
                        objectMapper.getTypeFactory().constructParametricType(PageResponse.class, ClassesResponse.class)
                )
        );
    }

    public ApiResponse<ClassesResponse> getClassesById(Long id) throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "class/info/" + id);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public ApiResponse<ClassesResponse> createNewClass(CreateNewClassRequest request) throws IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.post(Environment.url + "class/new", body);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public ApiResponse<ClassesResponse> updateClassById(UpdateClassRequest request, Long id) throws IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.put(Environment.url + "class/update/" + id, body);
        return objectMapper.readValue(jsonResponse,
                new TypeReference<>() {
                });
    }

    public void deleteClassById(Long id) throws IOException {
       HttpClient.delete(Environment.url + "class/delete/" + id);
    }
}
