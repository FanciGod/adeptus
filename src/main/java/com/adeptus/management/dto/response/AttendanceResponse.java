package com.adeptus.management.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AttendanceResponse {
    private Long id;
    private Long classId;
    private Long teacherId;
    private Set<Long> studentIds;
    private LocalDateTime date;
}

