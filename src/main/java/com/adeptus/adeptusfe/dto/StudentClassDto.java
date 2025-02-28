package com.adeptus.adeptusfe.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentClassDto {
    private Long id;
    private StudentDto studentDto;
    private ClassesDto classesDto;
}
