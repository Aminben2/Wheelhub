package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.VehicleDTO;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.entity.User;

public class VehicleMapper {

    public static VehicleDTO entityToDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        return VehicleDTO.builder()
                .id(vehicle.getId())
                .sellerId(vehicle.getSeller() != null ? vehicle.getSeller().getId() : null)
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .mileage(vehicle.getMileage())
                .price(vehicle.getPrice())
                .description(vehicle.getDescription())
                .location(vehicle.getLocation())
                .createdAt(vehicle.getCreatedAt())
                .updatedAt(vehicle.getUpdatedAt())
                .build();
    }

    public static Vehicle dtoToEntity(VehicleDTO vehicleDTO) {
        if (vehicleDTO == null) {
            return null;
        }

        Vehicle.VehicleBuilder vehicleBuilder = Vehicle.builder()
                .id(vehicleDTO.getId())
                .make(vehicleDTO.getMake())
                .model(vehicleDTO.getModel())
                .year(vehicleDTO.getYear())
                .mileage(vehicleDTO.getMileage())
                .price(vehicleDTO.getPrice())
                .description(vehicleDTO.getDescription())
                .location(vehicleDTO.getLocation())
                .createdAt(vehicleDTO.getCreatedAt())
                .updatedAt(vehicleDTO.getUpdatedAt());

        if (vehicleDTO.getSellerId() != null) {
            User seller = new User();
            seller.setId(vehicleDTO.getSellerId());
            vehicleBuilder.seller(seller);
        }

        return vehicleBuilder.build();
    }
}
