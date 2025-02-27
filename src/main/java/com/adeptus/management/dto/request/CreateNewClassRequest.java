package com.adeptus.management.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewClassRequest {
    private String className;
    private Long pricePerSession;
    private Long teacherId;
    private Long staffId;
    private Long courseId;

}
