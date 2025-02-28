package com.adeptus.adeptusfe.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String studentName;
    private String phone;
    private String email;
    private LocalDate dob;
}
