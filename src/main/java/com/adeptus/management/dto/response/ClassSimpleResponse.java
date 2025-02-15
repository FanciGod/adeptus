package com.adeptus.management.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassSimpleResponse {
    private Long id;
    private String className;
}
