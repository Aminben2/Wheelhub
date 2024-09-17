package com.WheelHub.WheelHub.dto.vehicleTypeDtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class VehicleTypeResponseDto {
    private Long id;
    private String typeName;
}
