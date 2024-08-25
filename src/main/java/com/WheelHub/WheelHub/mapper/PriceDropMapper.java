package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.PriceDropDTO;
import com.WheelHub.WheelHub.entity.PriceDrop;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class PriceDropMapper {

    public static PriceDropDTO entityToDTO(PriceDrop priceDrop) {
        return PriceDropDTO.builder()
                .id(priceDrop.getId())
                .vehicleId(priceDrop.getVehicle() != null ? priceDrop.getVehicle().getId() : null)
                .oldPrice(priceDrop.getOldPrice())
                .newPrice(priceDrop.getNewPrice())
                .droppedAt(priceDrop.getDroppedAt())
                .build();
    }

    public static PriceDrop dtoToEntity(PriceDropDTO priceDropDTO) {
        PriceDrop priceDrop = PriceDrop.builder()
                .id(priceDropDTO.getId())
                .oldPrice(priceDropDTO.getOldPrice())
                .newPrice(priceDropDTO.getNewPrice())
                .droppedAt(priceDropDTO.getDroppedAt())
                .build();

        if (priceDropDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(priceDropDTO.getVehicleId());
            priceDrop.setVehicle(vehicle);
        }

        return priceDrop;
    }
}
