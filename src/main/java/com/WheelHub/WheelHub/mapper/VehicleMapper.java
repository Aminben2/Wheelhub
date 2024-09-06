package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleDto;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;


@Component
public class VehicleMapper {

    public static VehicleDto entityToDTO(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        return VehicleDto.builder()
                .sellerId(vehicle.getSeller() != null ? vehicle.getSeller().getId() : null)
                .make(vehicle.getMake())
                .model(vehicle.getModel())
                .year(vehicle.getYear())
                .mileage(vehicle.getMileage())
                .price(vehicle.getPrice())
                .description(vehicle.getDescription())
                .location(vehicle.getLocation())
                .build();
    }

    public static Vehicle dtoToEntity(VehicleDto vehicleDTO) {
        if (vehicleDTO == null) {
            return null;
        }

        Vehicle.VehicleBuilder vehicleBuilder = Vehicle.builder()
                .make(vehicleDTO.getMake())
                .model(vehicleDTO.getModel())
                .year(vehicleDTO.getYear())
                .mileage(vehicleDTO.getMileage())
                .price(vehicleDTO.getPrice())
                .description(vehicleDTO.getDescription())
                .location(vehicleDTO.getLocation());

        if (vehicleDTO.getSellerId() != null) {
            User seller = new User();
            seller.setId(vehicleDTO.getSellerId());
            vehicleBuilder.seller(seller);
        }

        return vehicleBuilder.build();
    }
}
