package com.adeptus.management.controller;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.student.AddStudentToClassRequest;
import com.adeptus.management.dto.request.student.CreateStudentRequest;
import com.adeptus.management.dto.request.student.UpdateStudentRequest;
import com.adeptus.management.dto.response.StudentResponse;
import com.adeptus.management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@RequestBody @Valid CreateStudentRequest request) {
        return ResponseEntity.ok(studentService.createStudent(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Long id, @RequestBody @Valid UpdateStudentRequest request) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable Long id) {
        ApiResponse<String> response = studentService.deleteStudent(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add-to-class")
    public ResponseEntity<ApiResponse<String>> addStudentToClass(@RequestBody @Valid AddStudentToClassRequest request) {
        ApiResponse<String> response = studentService.addStudentToClass(request);
        return ResponseEntity.ok(response);
    }
}
