package com.WheelHub.WheelHub.dto.vehicleCategoryDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleCategoryDto {
    @NotNull
    @NotBlank
    private String categoryName;
}
