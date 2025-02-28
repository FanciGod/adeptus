package com.adeptus.adeptusfe.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateClassRequest {
    private String className;
    private Long pricePerSession;
}
