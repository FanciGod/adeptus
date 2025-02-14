package com.adeptus.management.dto.request.teacher;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherSalaryHistoryUpdateRequest {
    private Long salary;
    private LocalDate startDate;
    private LocalDate endDate;
}
