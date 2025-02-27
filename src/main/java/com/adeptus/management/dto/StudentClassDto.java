package com.adeptus.management.dto;

import com.adeptus.management.entity.Classes;
import com.adeptus.management.entity.Student;
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
