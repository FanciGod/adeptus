package com.adeptus.management.controller;

import com.adeptus.management.dto.ApiResponse;
import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("staff")
@RequiredArgsConstructor
public class StaffController {
    private final StaffService staffService;

    @GetMapping("all")
    public ApiResponse<Page<StaffResponse>> getAllStaffWithPagination(@RequestParam int page,@RequestParam int size){
        return ApiResponse.<Page<StaffResponse>>builder()
                .result(staffService.getAllStaffWithPagination(page,size))
                .build();
    }

    @PostMapping("new")
    public ApiResponse<StaffResponse> createNewStaff(@ModelAttribute CreateNewStaffRequest request) throws IOException {
        return ApiResponse.<StaffResponse>builder()
                .result(staffService.createNewStaff(request))
                .build();
    }
}
