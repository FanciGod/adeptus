package com.adeptus.management.mapper;

import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryCreateRequest;
import com.adeptus.management.dto.request.teacher.TeacherSalaryHistoryUpdateRequest;
import com.adeptus.management.dto.response.TeacherSalaryHistoryResponse;
import com.adeptus.management.entity.teacher.TeacherSalaryHistory;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface TeacherSalaryHistoryMapper {

    @Mapping(source = "teacher.id", target = "teacherId")
    TeacherSalaryHistoryResponse toResponse(TeacherSalaryHistory entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    TeacherSalaryHistory toEntity(TeacherSalaryHistoryCreateRequest dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher", ignore = true)
    void updateEntity(@MappingTarget TeacherSalaryHistory entity, TeacherSalaryHistoryUpdateRequest dto);
}
