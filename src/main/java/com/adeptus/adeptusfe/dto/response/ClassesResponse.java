package com.adeptus.adeptusfe.dto.response;

import com.adeptus.adeptusfe.dto.CourseDto;
import com.adeptus.adeptusfe.dto.StaffDto;
import com.adeptus.adeptusfe.dto.StudentClassDto;
import com.adeptus.adeptusfe.dto.TeacherDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

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
