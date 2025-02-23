package com.adeptus.management.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentClassResponse {
    private Long id;
    private String className;
    private Integer lessonRemain;
    private Long totalPaid;
}

