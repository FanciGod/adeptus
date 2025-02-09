package com.adeptus.management.dto.request.staff;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStaffRequest {
    @NotBlank(message = "Username không được để trống")
    private String username;

    @NotBlank(message = "Full name không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotNull(message = "Ngày sinh không được để trống")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) // định dạng yyyy-MM-dd
    private LocalDate dob;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại không hợp lệ")
    private String phone;

    private Long salary;

    private Set<Long> roleId;

    private Set<Long> classId;
}
