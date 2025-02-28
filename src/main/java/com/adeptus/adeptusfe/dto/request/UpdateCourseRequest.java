package com.adeptus.adeptusfe.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCourseRequest {
    private String name;
    private String description;
}
