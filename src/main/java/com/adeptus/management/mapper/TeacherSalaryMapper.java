package com.adeptus.management.mapper;

import com.adeptus.management.dto.TeacherSalaryDto;
import com.adeptus.management.entity.TeacherSalary;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TeacherSalaryMapper {
    TeacherSalaryDto toTeacherSalaryDto(TeacherSalary teacherSalary);
}
