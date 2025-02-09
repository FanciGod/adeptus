package com.adeptus.management.controller;

import com.adeptus.management.dto.request.teacher.CreateTeacherRequest;
import com.adeptus.management.dto.request.teacher.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import com.adeptus.management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherResponse>> getAllTeachers() {
        List<TeacherResponse> responses = teacherService.getAllTeachers();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TeacherResponse>> getAllTeachersWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TeacherResponse> responses = teacherService.getAllTeachersWithPagination(page, size);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getTeacherById(@PathVariable Long id) {
        TeacherResponse response = teacherService.getTeacherById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<TeacherResponse> createTeacher(
            @Valid @RequestBody CreateTeacherRequest request) {
        TeacherResponse response = teacherService.createTeacher(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherResponse> updateTeacher(
            @PathVariable Long id,
            @Valid @RequestBody UpdateTeacherRequest request) {
        TeacherResponse response = teacherService.updateTeacher(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteTeacher(@PathVariable Long id) {
        teacherService.deleteTeacher(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Teacher deleted successfully");
        return ResponseEntity.ok(response);
    }
}
