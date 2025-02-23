package com.adeptus.management.dto.request.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddStudentToClassRequest {
    private Long studentId;
    private Long classId;
    private Integer lessonRemain;
    private Long totalPaid;
}
