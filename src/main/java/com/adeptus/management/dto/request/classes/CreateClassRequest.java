package com.adeptus.management.dto.request.classes;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateClassRequest {

    @NotBlank(message = "Tên lớp học không được để trống")
    @Size(max = 20, message = "Tên lớp học không được vượt quá 20 ký tự")
    private String className;

    @NotNull(message = "Giá mỗi buổi học không được để trống")
    private Long pricePerSession;

    @NotNull(message = "Khóa học không được để trống")
    private Long courseId;

    @NotNull(message = "Giáo viên không được để trống")
    private Long teacherId;

    @NotNull(message = "Nhân viên quản lý không được để trống")
    private Long staffId;
}
