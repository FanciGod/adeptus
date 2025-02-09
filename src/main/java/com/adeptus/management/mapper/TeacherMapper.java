package com.adeptus.management.mapper;

import com.adeptus.management.dto.request.teacher.CreateTeacherRequest;
import com.adeptus.management.dto.request.teacher.UpdateTeacherRequest;
import com.adeptus.management.dto.response.TeacherResponse;
import com.adeptus.management.entity.teacher.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    // Chuyển Teacher sang TeacherResponse; ánh xạ isActive từ BaseEntity
    @Mapping(target = "isActive", source = "isActive")
    TeacherResponse toTeacherResponse(Teacher teacher);

    // Chuyển CreateTeacherRequest sang Teacher entity
    // Các trường khác (như id, isActive) sẽ được ignore hoặc được set mặc định
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Teacher toTeacher(CreateTeacherRequest request);

    // Cập nhật Teacher từ UpdateTeacherRequest
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateTeacherFromRequest(UpdateTeacherRequest request, @MappingTarget Teacher teacher);
}
