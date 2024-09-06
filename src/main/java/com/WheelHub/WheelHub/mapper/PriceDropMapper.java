package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
import com.WheelHub.WheelHub.entity.PriceDrop;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class PriceDropMapper {

    public static PriceDropDto entityToDTO(PriceDrop priceDrop) {
        return PriceDropDto.builder()
                .vehicleId(priceDrop.getVehicle() != null ? priceDrop.getVehicle().getId() : null)
                .oldPrice(priceDrop.getOldPrice())
                .newPrice(priceDrop.getNewPrice())
                .droppedAt(priceDrop.getDroppedAt())
                .build();
    }

    public static PriceDrop dtoToEntity(PriceDropDto priceDropDTO) {
        PriceDrop priceDrop = PriceDrop.builder()
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
