package com.adeptus.management.controller;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.CreateNewTeacherRequest;
import com.adeptus.management.dto.request.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import com.adeptus.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("all")
    public ApiResponse<List<TeacherResponse>> getAllTeacher() {
        return ApiResponse.<List<TeacherResponse>>builder()
                .result(teacherService.getAllActiveTeacher())
                .build();
    }

    @GetMapping("info/{id}")
    public ApiResponse<TeacherResponse> getTeacherById(@PathVariable Long id) {
        return ApiResponse.<TeacherResponse>builder()
                .result(teacherService.getTeacherById(id))
                .build();
    }

    @PostMapping("new")
    public ApiResponse<TeacherResponse> createNewTeacher(@RequestBody CreateNewTeacherRequest request) {
        return ApiResponse.<TeacherResponse>builder()
                .result(teacherService.CreateNewTeacher(request))
                .build();
    }

    @PutMapping("update/{id}")
    public ApiResponse<TeacherResponse> updateTeacherById(@RequestBody UpdateTeacherRequest request, @PathVariable Long id) {
        return ApiResponse.<TeacherResponse>builder()
                .result(teacherService.updateTeacherById(request, id))
                .build();
    }

    @DeleteMapping("delete/{id}")
    public ApiResponse<TeacherResponse> deleteTeacherById(@PathVariable Long id) {
        teacherService.deleteTeacherById(id);
        return ApiResponse.<TeacherResponse>builder().build();
    }
}
