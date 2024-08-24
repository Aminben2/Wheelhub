package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocalizationDTO {

    private Long id;
    private Long userId;
    private Long languageId;
    private Long currencyId;
    private LocalDateTime createdAt;
}
