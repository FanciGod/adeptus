package com.adeptus.management.dto.response;

import com.adeptus.management.dto.CourseDto;
import com.adeptus.management.dto.StaffDto;
import com.adeptus.management.dto.StudentClassDto;
import com.adeptus.management.dto.TeacherDto;
import com.adeptus.management.entity.StudentClass;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassesResponse {
    private Long id;
    private String className;
    private Long pricePerSession;
    private List<StudentClassDto> studentClassesDto;
    private TeacherDto teacherDto;
    private StaffDto staffDto;
    private CourseDto courseDto;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
