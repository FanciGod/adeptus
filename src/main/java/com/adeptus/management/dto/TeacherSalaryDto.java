package com.adeptus.management.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherSalaryDto {
    private Long id;
    private TeacherDto teacherDto;
    private Long salaryPerSession;
    private LocalDate startDate;
    private LocalDate endDate;
}
