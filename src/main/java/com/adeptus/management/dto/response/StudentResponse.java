package com.adeptus.management.dto.response;

import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String studentName;
    private String phone;
    private String email;
    private LocalDate dob;
    private Set<StudentClassResponse> studentClasses;
    private Set<MarkResponse> marks;
    private Boolean isActive;
}
