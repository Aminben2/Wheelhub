package com.WheelHub.WheelHub.dto.vehicleFeatureDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleFeatureResponseDto {
    private String featureName;
    private Long id;
    private String description;
}
