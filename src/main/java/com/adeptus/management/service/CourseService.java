package com.adeptus.management.service;

import com.adeptus.management.dto.request.classes.CourseRequest;
import com.adeptus.management.dto.response.CourseResponse;

import java.util.List;

public interface CourseService {
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(Long id);
    CourseResponse createCourse(CourseRequest request);
    CourseResponse updateCourse(Long id, CourseRequest request);
    void deleteCourse(Long id);
}
