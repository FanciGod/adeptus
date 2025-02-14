package com.adeptus.management.service;

import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.management.dto.response.StaffResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;

public interface StaffService {
    Page<StaffResponse> getAllStaffWithPagination(int page, int size);

    StaffResponse getStaffById(Long id);

    StaffResponse createNewStaff(CreateNewStaffRequest request) throws IOException;

    StaffResponse updateStaffBasicInfoById(UpdateStaffBasicInfoRequest request, Long id);

    void deleteStaffById(Long id);
}
