package com.adeptus.adeptusfe.dto.response;


import com.adeptus.adeptusfe.dto.ClassesDto;
import com.adeptus.adeptusfe.dto.RoleDto;
import com.adeptus.adeptusfe.dto.StaffSalaryDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaffResponse {
    private Long id;
    private String username;
    private String fullName;
    private String email;
    private String phone;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;
    private List<StaffSalaryDto> salaries;
    private List<RoleDto> roles;
    private Set<ClassesDto> classes;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private Boolean isActive;
}
