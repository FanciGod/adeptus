package com.adeptus.management.dto.response;

import com.adeptus.management.dto.ClassesDto;
import com.adeptus.management.dto.TeacherSalaryDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherResponse {
    private Long id;
    private String name;
    private String phone;
    private List<TeacherSalaryDto> teacherSalaryDtoList;
    private List<ClassesDto> classesDtoList;

}
