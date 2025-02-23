package com.adeptus.management.mapper;
import com.adeptus.management.dto.request.student.CreateStudentRequest;
import com.adeptus.management.dto.request.student.UpdateStudentRequest;
import com.adeptus.management.dto.response.StudentResponse;
import com.adeptus.management.entity.student.Student;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface StudentMapper {
    StudentResponse toStudentResponse(Student student);

    Student toStudent(CreateStudentRequest request);

    void updateStudentFromRequest(UpdateStudentRequest request, @MappingTarget Student student);
}
