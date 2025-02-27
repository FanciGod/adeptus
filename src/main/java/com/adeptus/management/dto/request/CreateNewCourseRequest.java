package com.adeptus.management.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewCourseRequest {
    private String name;
    private String description;
}
