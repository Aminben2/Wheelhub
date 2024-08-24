package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.DealershipInventoryDTO;

import java.util.List;

public interface DealershipInventoryService {

    DealershipInventoryDTO createDealershipInventory(DealershipInventoryDTO dealershipInventoryDTO);

    DealershipInventoryDTO getDealershipInventoryById(Long id);

    List<DealershipInventoryDTO> getAllDealershipInventories();

    DealershipInventoryDTO updateDealershipInventory(Long id, DealershipInventoryDTO dealershipInventoryDTO);

    void deleteDealershipInventory(Long id);
}
