package com.adeptus.management.controller;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.CreateNewCourseRequest;
import com.adeptus.management.dto.request.UpdateCourseRequest;
import com.adeptus.management.dto.response.CourseResponse;
import com.adeptus.management.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("course")
@RequiredArgsConstructor
public class CourseController {
    private final CourseService courseService;

    @GetMapping("all")
    public ApiResponse<List<CourseResponse>> getAllCourses(){
        return ApiResponse.<List<CourseResponse>>builder()
                .result(courseService.getAllCourses())
                .build();
    }

    @PostMapping("new")
    public ApiResponse<CourseResponse> createNewCourse(@RequestBody CreateNewCourseRequest request){
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.createNewCourse(request))
                .build();
    }

    @PutMapping("update/{id}")
    public ApiResponse<CourseResponse> updateCourseById(@RequestBody UpdateCourseRequest request,@PathVariable Long id){
        return ApiResponse.<CourseResponse>builder()
                .result(courseService.updateCourseById(request,id))
                .build();
    }

    @DeleteMapping("delete/{id}")
    public ApiResponse<?> deleteCourseById(@PathVariable Long id){
        courseService.deleteCourseById(id);
    return ApiResponse.builder().build();
    }
}

