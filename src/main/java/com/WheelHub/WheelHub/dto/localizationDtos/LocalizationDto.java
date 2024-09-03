package com.WheelHub.WheelHub.dto.localizationDtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LocalizationDto {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Language ID is mandatory")
    private Long languageId;

    @NotNull(message = "Currency ID is mandatory")
    private Long currencyId;
}
