package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;
import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeResponseDto;
import com.WheelHub.WheelHub.entity.VehicleType;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper {

    public static VehicleTypeDto entityToDTO(VehicleType vehicleType) {
        return VehicleTypeDto.builder()
                .typeName(vehicleType.getTypeName())
                .build();
    }

    public static VehicleTypeResponseDto entityToResponseDTO(VehicleType vehicleType) {
        return VehicleTypeResponseDto.builder()
                .id(vehicleType.getId())
                .typeName(vehicleType.getTypeName())
                .build();
    }

    public static VehicleType dtoToEntity(VehicleTypeDto vehicleTypeDto) {
        return VehicleType.builder()
                .typeName(vehicleTypeDto.getTypeName())
                .build();
    }
}
