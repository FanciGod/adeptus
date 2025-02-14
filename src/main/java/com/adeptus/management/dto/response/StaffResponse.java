package com.adeptus.management.dto.response;

import com.adeptus.management.dto.ClassesDto;
import com.adeptus.management.dto.RoleDto;
import com.adeptus.management.dto.StaffSalaryDto;
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
    private String thumbnailUrl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
    private Boolean isActive;
}
