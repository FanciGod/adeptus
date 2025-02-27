package com.adeptus.management.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewTeacherRequest {
    private String name;
    private String phone;
    private Long salaryPerSession;
}
