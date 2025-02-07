package com.adeptus.management.service;

import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.entity.staff.Staff;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface StaffService {
    List<StaffResponse> getAllStaffs();
    Page<StaffResponse> getAllStaffWithPagination(int page, int size);

    StaffResponse createNewStaff(CreateNewStaffRequest request) throws IOException;

    StaffResponse updateStaff(Long id, CreateNewStaffRequest request) throws IOException;
    void deleteStaff(Long id);
}
