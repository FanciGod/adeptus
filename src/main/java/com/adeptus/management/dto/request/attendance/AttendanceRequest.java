package com.adeptus.management.dto.request.attendance;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceRequest {

    @NotNull(message = "Class ID không được để trống")
    private Long classId;

    @NotNull(message = "Teacher ID không được để trống")
    private Long teacherId;

    @NotEmpty(message = "Danh sách học viên không được để trống")
    private Set<Long> studentIds;
}