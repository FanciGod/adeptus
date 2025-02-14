package com.adeptus.adeptusfe.dto.request;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateStaffBasicInfoRequest {
    private String fullName;
    private String email;
    private LocalDate dob;
    private String phone;
}
