package com.adeptus.management.dto.request.teacher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateTeacherRequest {

    @NotBlank(message = "Tên giáo viên không được để trống")
    @Size(max = 50, message = "Tên giáo viên không được vượt quá 50 ký tự")
    private String name;
}