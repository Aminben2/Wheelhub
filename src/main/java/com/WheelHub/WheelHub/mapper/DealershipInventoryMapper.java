package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.DealershipInventoryDTO;
import com.WheelHub.WheelHub.entity.Dealership;
import com.WheelHub.WheelHub.entity.DealershipInventory;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class DealershipInventoryMapper {

    public static DealershipInventoryDTO entityToDTO(DealershipInventory inventory) {
        return DealershipInventoryDTO.builder()
                .id(inventory.getId())
                .dealershipId(inventory.getDealership() != null ? inventory.getDealership().getId() : null)
                .vehicleId(inventory.getVehicle() != null ? inventory.getVehicle().getId() : null)
                .stock(inventory.getStock())
                .createdAt(inventory.getCreatedAt())
                .updatedAt(inventory.getUpdatedAt())
                .build();
    }

    public static DealershipInventory dtoToEntity(DealershipInventoryDTO inventoryDTO) {
        DealershipInventory inventory = DealershipInventory.builder()
                .id(inventoryDTO.getId())
                .stock(inventoryDTO.getStock())
                .createdAt(inventoryDTO.getCreatedAt())
                .updatedAt(inventoryDTO.getUpdatedAt())
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
