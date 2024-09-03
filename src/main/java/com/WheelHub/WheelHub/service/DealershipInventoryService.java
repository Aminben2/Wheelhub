package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.DealershipInventoryDtos.DealershipInventoryDto;

import java.util.List;

public interface DealershipInventoryService {

    DealershipInventoryDto createDealershipInventory(DealershipInventoryDto dealershipInventoryDTO);

    DealershipInventoryDto getDealershipInventoryById(Long id);

    List<DealershipInventoryDto> getAllDealershipInventories();

    DealershipInventoryDto updateDealershipInventory(Long id, DealershipInventoryDto dealershipInventoryDTO);

    void deleteDealershipInventory(Long id);
}
