package com.adeptus.management.dto.request.student;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TuitionPaymentRequest {
    @NotNull(message = "Student ID không được để trống")
    private Long studentId;

    @NotNull(message = "Số tiền không được để trống")
    @Min(value = 100000, message = "Số tiền tối thiểu là 100,000 VND")
    private Long amount;

    @NotNull(message = "Số buổi học không được để trống")
    @Min(value = 1, message = "Số buổi học ít nhất là 1")
    private Integer lessonPurchased;

    @Min(value = 0, message = "Giảm giá không thể nhỏ hơn 0")
    @Max(value = 100, message = "Giảm giá không thể lớn hơn 100%")
    private int discount;

    @Size(max = 500, message = "Ghi chú không được quá 500 ký tự")
    private String note;
}
