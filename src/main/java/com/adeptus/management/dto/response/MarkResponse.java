package com.adeptus.management.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MarkResponse {
    private Long id;
    private String courseName;
    private String examName;
    private Float mark;
}
