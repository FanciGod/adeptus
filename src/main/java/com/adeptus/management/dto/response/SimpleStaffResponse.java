package com.adeptus.management.dto.response;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SimpleStaffResponse {
    private Long id;
    private String name;
}
