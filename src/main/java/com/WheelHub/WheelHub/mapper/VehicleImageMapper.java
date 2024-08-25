package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.VehicleImageDTO;
import com.WheelHub.WheelHub.entity.VehicleImage;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class VehicleImageMapper {

    public static VehicleImageDTO entityToDTO(VehicleImage vehicleImage) {
        if (vehicleImage == null) {
            return null;
        }

        return VehicleImageDTO.builder()
                .id(vehicleImage.getId())
                .vehicleId(vehicleImage.getVehicle() != null ? vehicleImage.getVehicle().getId() : null)
                .imageUrl(vehicleImage.getImageUrl())
                .createdAt(vehicleImage.getCreatedAt())
                .build();
    }

    public static VehicleImage dtoToEntity(VehicleImageDTO vehicleImageDTO) {
        if (vehicleImageDTO == null) {
            return null;
        }

        VehicleImage.VehicleImageBuilder vehicleImageBuilder = VehicleImage.builder()
                .id(vehicleImageDTO.getId())
                .imageUrl(vehicleImageDTO.getImageUrl())
                .createdAt(vehicleImageDTO.getCreatedAt());

        if (vehicleImageDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(vehicleImageDTO.getVehicleId());
            vehicleImageBuilder.vehicle(vehicle);
        }

        return vehicleImageBuilder.build();
    }
}
