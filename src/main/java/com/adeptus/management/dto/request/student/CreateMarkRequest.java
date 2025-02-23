package com.adeptus.management.dto.request.student;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateMarkRequest {
    private Long studentId;
    private Long courseId;
    private String examName;
    private Float mark;
}
