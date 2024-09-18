package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.DealershipInventoryDtos.DealershipInventoryDto;
import com.WheelHub.WheelHub.entity.Dealership;
import com.WheelHub.WheelHub.entity.DealershipInventory;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.DealershipInventoryMapper;
import com.WheelHub.WheelHub.repository.DealershipInventoryRepository;
import com.WheelHub.WheelHub.repository.DealershipRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.DealershipInventoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DealershipInventoryServiceImpl implements DealershipInventoryService {

  private final DealershipInventoryRepository dealershipInventoryRepository;
  private final DealershipRepository dealershipRepository;
  private final VehicleRepository vehicleRepository;

  @Override
  @Transactional
  public DealershipInventoryDto createDealershipInventory(
      DealershipInventoryDto dealershipInventoryDTO) {
    Dealership dealership =
        dealershipRepository
            .findById(dealershipInventoryDTO.getDealershipId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Dealership not found for id:" + dealershipInventoryDTO.getDealershipId()));
    Vehicle vehicle =
        vehicleRepository
            .findById(dealershipInventoryDTO.getVehicleId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Vehicle not found for id:" + dealershipInventoryDTO.getVehicleId()));

    DealershipInventory dealershipInventory =
        DealershipInventoryMapper.dtoToEntity(dealershipInventoryDTO);
    dealershipInventory.setDealership(dealership);
    dealershipInventory.setVehicle(vehicle);

    dealershipInventory = dealershipInventoryRepository.save(dealershipInventory);
    return DealershipInventoryMapper.entityToDTO(dealershipInventory);
  }

  @Override
  public DealershipInventoryDto getDealershipInventoryById(Long id) {
    return dealershipInventoryRepository
        .findById(id)
        .map(DealershipInventoryMapper::entityToDTO)
        .orElseThrow(
            () -> new EntityNotFoundException("DealershipInventory not found for id:" + id));
  }

  @Override
  public List<DealershipInventoryDto> getAllDealershipInventories() {
    return dealershipInventoryRepository.findAll().stream()
        .map(DealershipInventoryMapper::entityToDTO)
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public DealershipInventoryDto updateDealershipInventory(
      Long id, DealershipInventoryDto dealershipInventoryDTO) {
    DealershipInventory existingDealershipInventory =
        dealershipInventoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("DealershipInventory not found for id:" + id));

    Dealership dealership =
        dealershipRepository
            .findById(dealershipInventoryDTO.getDealershipId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Dealership not found for id:" + dealershipInventoryDTO.getDealershipId()));
    Vehicle vehicle =
        vehicleRepository
            .findById(dealershipInventoryDTO.getVehicleId())
            .orElseThrow(
                () ->
                    new EntityNotFoundException(
                        "Vehicle not found for id:" + dealershipInventoryDTO.getVehicleId()));

    existingDealershipInventory.setDealership(dealership);
    existingDealershipInventory.setVehicle(vehicle);
    existingDealershipInventory.setStock(dealershipInventoryDTO.getStock());

    DealershipInventory updatedDealershipInventory =
        dealershipInventoryRepository.save(existingDealershipInventory);
    return DealershipInventoryMapper.entityToDTO(updatedDealershipInventory);
  }

  @Override
  public void deleteDealershipInventory(Long id) {
    DealershipInventory dealershipInventory =
        dealershipInventoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("DealershipInventory not found for id:" + id));
    dealershipInventoryRepository.delete(dealershipInventory);
  }
}
