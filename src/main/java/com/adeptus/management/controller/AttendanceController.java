package com.adeptus.management.controller;

import com.adeptus.management.dto.request.attendance.AttendanceRequest;
import com.adeptus.management.dto.response.AttendanceResponse;
import com.adeptus.management.service.IAttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final IAttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponse> markAttendance(
            @Valid @RequestBody AttendanceRequest request) {
        return ResponseEntity.ok(attendanceService.markAttendance(request));
    }

    @GetMapping("/class/{id}")
    public ResponseEntity<List<AttendanceResponse>> getAttendanceByClass(@PathVariable Long id) {
        return ResponseEntity.ok(attendanceService.getAttendanceByClass(id));
    }
}

