package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.request.CreateNewTeacherRequest;
import com.adeptus.adeptusfe.dto.request.UpdateTeacherRequest;
import com.adeptus.adeptusfe.dto.response.PageResponse;
import com.adeptus.adeptusfe.dto.response.StaffResponse;
import com.adeptus.adeptusfe.dto.response.TeacherResponse;
import com.adeptus.adeptusfe.utility.Environment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.IOException;
import java.util.List;

public class TeacherService {
    private final ObjectMapper objectMapper;

    public TeacherService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public ApiResponse<List<TeacherResponse>> getAllTeacher() throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "teacher/all");
        return objectMapper.readValue(
                jsonResponse, objectMapper.getTypeFactory().constructParametricType(
                        ApiResponse.class,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, TeacherResponse.class)
                )
        );
    }

    public ApiResponse<TeacherResponse> getTeacherById(Long id) throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "teacher/info/"+id);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public ApiResponse<TeacherResponse> createNewTeacher(CreateNewTeacherRequest request) throws IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.post(Environment.url + "teacher/new",body);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public ApiResponse<TeacherResponse> updateTeacherById(UpdateTeacherRequest request, Long id) throws IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.put(Environment.url + "teacher/update/" + id, body);
        return objectMapper.readValue(jsonResponse,
                new TypeReference<>() {
                });
    }

    public void deleteTeacherById(Long id) throws IOException {
        HttpClient.delete(Environment.url + "teacher/delete/" + id);
    }
}
