package com.adeptus.adeptusfe.dto.request;

import lombok.*;


import java.io.File;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateNewStaffRequest {
    private String username;
    private String password;
    private String rePassword;
    private String fullName;
    private String email;
    private LocalDate dob;
    private String phone;
    private File thumbnail;
    private Long salary;
    private Set<Long> roleId;
}
