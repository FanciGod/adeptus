package com.adeptus.management.mapper;
import com.adeptus.management.dto.request.student.CreateStudentRequest;
import com.adeptus.management.dto.request.student.UpdateStudentRequest;
import com.adeptus.management.dto.response.MarkResponse;
import com.adeptus.management.dto.response.StudentResponse;
import com.adeptus.management.entity.student.Mark;
import com.adeptus.management.entity.student.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "marks", expression = "java(mapMarks(student.getMarks()))")
    StudentResponse toStudentResponse(Student student);

    Student toStudent(CreateStudentRequest request);

    void updateStudentFromRequest(UpdateStudentRequest request, @MappingTarget Student student);

    // Ánh xạ danh sách điểm
    default Set<MarkResponse> mapMarks(Set<Mark> marks) {
        if (marks == null) return Set.of();

        return marks.stream().map(mark -> MarkResponse.builder()
                .id(mark.getId())
                .courseName(mark.getCourse() != null ? mark.getCourse().getName() : null) // Đảm bảo lấy tên khóa học
                .examName(mark.getExamName())
                .mark(mark.getMark())
                .build()
        ).collect(Collectors.toSet());
    }
}
