package com.adeptus.management.mapper;

import com.adeptus.management.dto.StudentDto;
import com.adeptus.management.entity.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentDto toStudentDto(Student student);
}
