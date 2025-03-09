package com.adeptus.management.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OtherExpensesResponseDTO {
    private Long id;
    private ExpensesCategoryDTO category; // Lưu toàn bộ đối tượng category
    private Long total;
    private LocalDate date;
    private String description;
}
