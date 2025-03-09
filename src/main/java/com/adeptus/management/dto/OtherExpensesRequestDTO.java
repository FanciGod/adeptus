package com.adeptus.management.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherExpensesRequestDTO {
    private Long categoryId; // Chỉ cần ID khi tạo/cập nhật
    private Long total;
    private LocalDate date;
    private String description;
}
