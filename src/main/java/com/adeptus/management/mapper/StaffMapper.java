package com.adeptus.management.mapper;

import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.entity.Staff;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffResponse toStaffResponse(Staff staff);
    Staff toStaff(CreateNewStaffRequest request);
}
