package com.adeptus.management.dto.response;

import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassDetailResponse {
    private Long id;
    private String className;
    private Long pricePerSession;
    private CourseResponse course;
    private TeacherResponse teacher;
    private SimpleStaffResponse staff;
    private Set<StudentResponse> students;
    private Boolean isActive;
}
