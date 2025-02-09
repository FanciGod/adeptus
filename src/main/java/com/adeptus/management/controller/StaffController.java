package com.adeptus.management.controller;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.staff.CreateNewStaffRequest;
import com.adeptus.management.dto.request.staff.UpdateStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.service.StaffService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @GetMapping("all")
    public ApiResponse<Page<StaffResponse>> getAllStaffWithPagination(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "10") int size){
        return ApiResponse.<Page<StaffResponse>>builder()
                .result(staffService.getAllStaffWithPagination(page,size))
                .build();
    }

    @PostMapping("new")
    public ApiResponse<StaffResponse> createNewStaff(@Valid @ModelAttribute CreateNewStaffRequest request) throws IOException {
        return ApiResponse.<StaffResponse>builder()
                .result(staffService.createNewStaff(request))
                .build();
    }

    @GetMapping()
    public ResponseEntity<List<StaffResponse>> getAllStaff() {
        List<StaffResponse> staffList = staffService.getAllStaffs();
        return ResponseEntity.ok(staffList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StaffResponse> updateStaff(@PathVariable Long id,
                                                     @Valid @ModelAttribute UpdateStaffRequest request) throws IOException {
        StaffResponse updatedStaff = staffService.updateStaff(id, request);
        return ResponseEntity.ok(updatedStaff);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
        return ResponseEntity.noContent().build();
    }
}
