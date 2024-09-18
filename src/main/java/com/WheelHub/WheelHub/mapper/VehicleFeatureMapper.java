package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureResponseDto;
import com.WheelHub.WheelHub.entity.VehicleFeature;
import org.springframework.stereotype.Component;

@Component
public class VehicleFeatureMapper {

  public static VehicleFeatureDto entityToDTO(VehicleFeature vehicleFeature) {
    return VehicleFeatureDto.builder()
        .description(vehicleFeature.getDescription())
        .featureName(vehicleFeature.getFeatureName())
        .build();
  }

  public static VehicleFeatureResponseDto entityToResponseDTO(VehicleFeature vehicleFeature) {
    return VehicleFeatureResponseDto.builder()
        .id(vehicleFeature.getId())
        .description(vehicleFeature.getDescription())
        .featureName(vehicleFeature.getFeatureName())
        .build();
  }

  public static VehicleFeature dtoToEntity(VehicleFeatureDto vehicleFeatureDto) {
    return VehicleFeature.builder()
        .featureName(vehicleFeatureDto.getFeatureName())
        .description(vehicleFeatureDto.getDescription())
        .build();
  }
}
