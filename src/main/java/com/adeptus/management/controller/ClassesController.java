package com.adeptus.management.controller;

import com.adeptus.management.dto.request.classes.CreateClassRequest;
import com.adeptus.management.dto.request.classes.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassDetailResponse;
import com.adeptus.management.service.ClassesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classes")
@RequiredArgsConstructor
public class ClassesController {
    private final ClassesService classesService;

    @GetMapping
    public ResponseEntity<List<ClassDetailResponse>> getAllClasses() {
        return ResponseEntity.ok(classesService.getAllClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassDetailResponse> getClassById(@PathVariable Long id) {
        return ResponseEntity.ok(classesService.getClassById(id));
    }

    @PostMapping
    public ResponseEntity<ClassDetailResponse> createClass(@Valid @RequestBody CreateClassRequest request) {
        return ResponseEntity.ok(classesService.createClass(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassDetailResponse> updateClass(
            @PathVariable Long id,
            @Valid @RequestBody UpdateClassRequest request) {
        return ResponseEntity.ok(classesService.updateClass(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteClass(@PathVariable Long id) {
        classesService.deleteClass(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Class deleted successfully");
        return ResponseEntity.ok(response);
    }
}
