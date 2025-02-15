package com.adeptus.management.mapper;

import com.adeptus.management.dto.ClassesDto;
import com.adeptus.management.dto.request.classes.CreateClassRequest;
import com.adeptus.management.dto.request.classes.UpdateClassRequest;
import com.adeptus.management.dto.response.ClassDetailResponse;
import com.adeptus.management.dto.response.SimpleStaffResponse;
import com.adeptus.management.entity.classes.Classes;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, TeacherMapper.class, StaffMapper.class, StudentMapper.class})
public interface ClassesMapper {
    ClassesDto toClassesDto (Classes classes);

    @Mapping(target = "course", source = "course")
    @Mapping(target = "teacher", source = "teacher")
    @Mapping(target = "staff", expression = "java(mapToSimpleStaff(classes.getStaff()))")
//    @Mapping(target = "students", source = "studentClasses")
    ClassDetailResponse toClassResponse(Classes classes);

    default SimpleStaffResponse mapToSimpleStaff(com.adeptus.management.entity.staff.Staff staff) {
        if (staff == null) return null;
        return new SimpleStaffResponse(staff.getId(), staff.getFullName());
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    Classes toClasses(CreateClassRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    void updateClassesFromRequest(UpdateClassRequest request, @MappingTarget Classes classes);

}
