package com.WheelHub.WheelHub.dto.vehicleCategoryDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleCategoryResponseDto {
    private Long id;
    private String categoryName;
}
