package com.adeptus.management.service;

import com.adeptus.management.dto.request.CreateNewCourseRequest;
import com.adeptus.management.dto.request.UpdateCourseRequest;
import com.adeptus.management.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourses();

    CourseResponse createNewCourse(CreateNewCourseRequest request);

    CourseResponse updateCourseById(UpdateCourseRequest request, Long id);

    void deleteCourseById(Long id);
}
