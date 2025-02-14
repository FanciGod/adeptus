package com.adeptus.management.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherSalaryResponse {
    private Long id;
    private Long salary;
    private LocalDate startDate;
    private LocalDate endDate;
}
