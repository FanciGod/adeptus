package com.adeptus.management.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponse {
    private Long id;
    private String name;
    private Boolean isActive;
    private List<TeacherSalaryResponse> salaries;
    private List<ClassResponse> classes;
}
