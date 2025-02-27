package com.adeptus.management.mapper;

import com.adeptus.management.dto.TeacherDto;
import com.adeptus.management.dto.request.CreateNewTeacherRequest;
import com.adeptus.management.dto.request.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import com.adeptus.management.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherDto toTeacherDto(Teacher teacher);

    TeacherResponse toTeacherResponse(Teacher teacher);

    Teacher toTeacher(CreateNewTeacherRequest request);

    void updateTeacher(UpdateTeacherRequest request, @MappingTarget Teacher teacher);
}
