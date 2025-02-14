package com.adeptus.management.controller;

import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryCreateRequest;
import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryUpdateRequest;
import com.adeptus.management.dto.response.TeacherSalaryHistoryResponse;
import com.adeptus.management.service.ITeacherSalaryHistoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher-salaries")
public class TeacherSalaryHistoryController {

    private final ITeacherSalaryHistoryService service;

    public TeacherSalaryHistoryController(ITeacherSalaryHistoryService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherSalaryHistoryResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TeacherSalaryHistoryResponse>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<TeacherSalaryHistoryResponse>> getByTeacherId(@PathVariable Long teacherId) {
        return ResponseEntity.ok(service.getByTeacherId(teacherId));
    }

    @PostMapping
    public ResponseEntity<TeacherSalaryHistoryResponse> create(@RequestBody TeacherSalaryHistoryCreateRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TeacherSalaryHistoryResponse> update(@PathVariable Long id, @RequestBody TeacherSalaryHistoryUpdateRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Teacher salary history deleted successfully.");
    }
}
