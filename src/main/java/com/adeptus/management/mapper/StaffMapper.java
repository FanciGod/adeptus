package com.adeptus.management.mapper;

import com.adeptus.management.dto.request.CreateNewStaffRequest;
import com.adeptus.management.dto.request.UpdateStaffBasicInfoRequest;
import com.adeptus.management.dto.response.StaffResponse;
import com.adeptus.management.entity.Staff;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StaffMapper {
    StaffResponse toStaffResponse(Staff staff);
    Staff toStaff(CreateNewStaffRequest request);

    Staff toStaff(UpdateStaffBasicInfoRequest request);

    void updateStaff(UpdateStaffBasicInfoRequest request, @MappingTarget Staff staff);
}
