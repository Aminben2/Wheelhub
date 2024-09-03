package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.DealershipInventoryDtos.DealershipInventoryDto;
import com.WheelHub.WheelHub.entity.Dealership;
import com.WheelHub.WheelHub.entity.DealershipInventory;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class DealershipInventoryMapper {

    public static DealershipInventoryDto entityToDTO(DealershipInventory inventory) {
        return DealershipInventoryDto.builder()
                .dealershipId(inventory.getDealership() != null ? inventory.getDealership().getId() : null)
                .vehicleId(inventory.getVehicle() != null ? inventory.getVehicle().getId() : null)
                .stock(inventory.getStock())
                .build();
    }

    public static DealershipInventory dtoToEntity(DealershipInventoryDto inventoryDTO) {
        DealershipInventory inventory = DealershipInventory.builder()
                .stock(inventoryDTO.getStock())
                .build();

        if (inventoryDTO.getDealershipId() != null) {
            Dealership dealership = new Dealership();
            dealership.setId(inventoryDTO.getDealershipId());
            inventory.setDealership(dealership);
        }
        if (inventoryDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(inventoryDTO.getVehicleId());
            inventory.setVehicle(vehicle);
        }

        return inventory;
    }
}
