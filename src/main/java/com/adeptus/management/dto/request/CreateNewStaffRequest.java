package com.adeptus.management.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
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
    private MultipartFile thumbnail;
    private Long salary;
    private Set<Long> roleId;
    private Set<Long> classId;
}
