package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureResponseDto;
import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageResponseDto;
import com.WheelHub.WheelHub.entity.VehicleImage;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class VehicleImageMapper {

    public static VehicleImageDto entityToDTO(VehicleImage vehicleImage) {
        if (vehicleImage == null) {
            return null;
        }

        return VehicleImageDto.builder()
                .vehicleId(vehicleImage.getVehicle() != null ? vehicleImage.getVehicle().getId() : null)
                .imageUrl(vehicleImage.getImageUrl())
                .build();
    }


    public static VehicleImageResponseDto entityToResponseDTO(VehicleImage vehicleImage) {
        if (vehicleImage == null) {
            return null;
        }

        return VehicleImageResponseDto.builder()
                .id(vehicleImage.getId())
                .createdAt(vehicleImage.getCreatedAt())
                .updatedAt(vehicleImage.getUpdatedAt())
                .imageUrl(vehicleImage.getImageUrl())
                .build();
    }

    public static VehicleImage dtoToEntity(VehicleImageDto vehicleImageDTO) {
        if (vehicleImageDTO == null) {
            return null;
        }

        VehicleImage.VehicleImageBuilder vehicleImageBuilder = VehicleImage.builder()
                .imageUrl(vehicleImageDTO.getImageUrl());

        if (vehicleImageDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(vehicleImageDTO.getVehicleId());
            vehicleImageBuilder.vehicle(vehicle);
        }

        return vehicleImageBuilder.build();
    }
}
