package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
import com.WheelHub.WheelHub.entity.VehicleFeature;
import org.springframework.stereotype.Component;

@Component
public class VehicleFeatureMapper {

    public static VehicleFeatureDto entityToDTO(VehicleFeature vehicleFeature) {
        return VehicleFeatureDto.builder()
                .featureName(vehicleFeature.getFeatureName())
                .build();
    }

    public static VehicleFeature dtoToEntity(VehicleFeatureDto vehicleFeatureDto) {
        return VehicleFeature.builder()
                .featureName(vehicleFeatureDto.getFeatureName())
                .build();
    }
}

