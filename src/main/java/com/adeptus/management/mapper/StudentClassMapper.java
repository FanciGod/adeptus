package com.adeptus.management.mapper;

import com.adeptus.management.dto.StudentClassDto;
import com.adeptus.management.entity.StudentClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StudentClassMapper {
    @Mapping(target = "studentDto", source = "student")  // Map student → studentDto
    @Mapping(target = "classesDto", source = "classes")  // Map classes → classesDto
    StudentClassDto toStudentClassDto(StudentClass studentClass);
}
