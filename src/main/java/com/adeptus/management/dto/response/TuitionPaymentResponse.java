package com.adeptus.management.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuitionPaymentResponse {
    private Long id;
    private Long studentId;
    private LocalDate date;
    private Long amount;
    private Integer lessonPurchased;
    private int discount;
    private Long total;
    private String note;
}
