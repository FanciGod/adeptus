package com.adeptus.management.mapper;

import com.adeptus.management.dto.response.AttendanceResponse;
import com.adeptus.management.entity.attendance.ClassHistory;
import com.adeptus.management.entity.student.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface AttendanceMapper {

    @Mapping(source = "classes.id", target = "classId")
    @Mapping(source = "teacher.id", target = "teacherId")
    @Mapping(target = "studentIds", expression = "java(mapStudents(classHistory.getStudents()))")
    AttendanceResponse toResponse(ClassHistory classHistory);

    default Set<Long> mapStudents(Set<Student> students) {
        return students.stream().map(Student::getId).collect(Collectors.toSet());
    }
}
