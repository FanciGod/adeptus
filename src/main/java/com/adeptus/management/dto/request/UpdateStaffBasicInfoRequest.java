package com.adeptus.management.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStaffBasicInfoRequest {
    private String fullName;
    @Email(message = "Email không hợp lệ")
    private String email;
    private LocalDate dob;
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại phải bắt đầu bằng 03, 05, 07, 08, hoặc 09 và có 10 chữ số")
    private String phone;
}
