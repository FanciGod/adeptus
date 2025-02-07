package com.adeptus.management.mapper;

import com.adeptus.management.dto.StaffSalaryDto;
import com.adeptus.management.entity.staff.StaffSalary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StaffSalaryMapper {
    StaffSalaryDto toStaffSalaryDto (StaffSalary staffSalary);
}
