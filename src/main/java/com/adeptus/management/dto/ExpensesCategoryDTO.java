package com.adeptus.management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExpensesCategoryDTO {
    private Long id;
    private String name;
    private String description;
}
