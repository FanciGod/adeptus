package com.adeptus.management.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class TeacherSalaryHistoryResponse {
    private Long id;
    private Long teacherId;
    private Long salary;
    private LocalDate startDate;
    private LocalDate endDate;
}