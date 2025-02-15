package com.adeptus.management.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private Boolean isActive;
}
