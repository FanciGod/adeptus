package com.adeptus.management.service;

import com.adeptus.management.dto.request.staff.CreateNewStaffRequest;
import com.adeptus.management.dto.request.staff.UpdateStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface StaffService {
    List<StaffResponse> getAllStaffs();
    Page<StaffResponse> getAllStaffWithPagination(int page, int size);

    StaffResponse createNewStaff(CreateNewStaffRequest request) throws IOException;

    StaffResponse updateStaff(Long id, UpdateStaffRequest request) throws IOException;
    void deleteStaff(Long id);
}
