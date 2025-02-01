package com.adeptus.management.service;

import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StaffService {
    Page<StaffResponse> getAllStaffWithPagination(int page, int size);

    StaffResponse createNewStaff(CreateNewStaffRequest request) throws IOException;
}
