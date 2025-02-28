package com.adeptus.adeptusfe.service;

import com.adeptus.adeptusfe.dto.ApiResponse;
import com.adeptus.adeptusfe.dto.request.CreateNewCourseRequest;
import com.adeptus.adeptusfe.dto.request.UpdateCourseRequest;
import com.adeptus.adeptusfe.dto.response.CourseResponse;
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

public class CourseService {
    private final ObjectMapper objectMapper;

    public CourseService() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    public ApiResponse<List<CourseResponse>> getAllCourse() throws IOException {
        String jsonResponse = HttpClient.get(Environment.url + "course/all");
        return objectMapper.readValue(
                jsonResponse, objectMapper.getTypeFactory().constructParametricType(
                        ApiResponse.class,
                        objectMapper.getTypeFactory().constructCollectionType(List.class, CourseResponse.class)
                )
        );
    }

    public ApiResponse<CourseResponse> createNewCourse(CreateNewCourseRequest request) throws IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.post(Environment.url + "course/new",body);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public ApiResponse<CourseResponse> updateCourseById(UpdateCourseRequest request, Long id) throws IOException {
        String jsonRequest = objectMapper.writeValueAsString(request);
        RequestBody body = RequestBody.create(jsonRequest, MediaType.parse("application/json"));
        String jsonResponse = HttpClient.put(Environment.url + "course/update/"+id,body);
        return objectMapper.readValue(jsonResponse, new TypeReference<>() {
        });
    }

    public void deleteCourseById(Long id) throws IOException {
        String jsonResponse = HttpClient.delete(Environment.url + "course/delete/" + id);
    }
}
