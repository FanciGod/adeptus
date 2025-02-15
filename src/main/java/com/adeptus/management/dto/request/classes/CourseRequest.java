package com.adeptus.management.dto.request.classes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {

    @NotBlank(message = "Tên khóa học không được để trống")
    @Size(max = 100, message = "Tên khóa học không được vượt quá 100 ký tự")
    private String name;

    @NotBlank(message = "Mô tả khóa học không được để trống")
    @Size(max = 500, message = "Mô tả khóa học không được vượt quá 500 ký tự")
    private String description;
}
