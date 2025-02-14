package com.adeptus.management.dto.request.teacher;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherSalaryHistoryCreateRequest {
    private Long teacherId;
    private Long salary;
    private LocalDate startDate;
    private LocalDate endDate;
}
