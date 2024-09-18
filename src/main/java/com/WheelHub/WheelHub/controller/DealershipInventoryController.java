package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.DealershipInventoryDtos.DealershipInventoryDto;
import com.WheelHub.WheelHub.service.impl.DealershipInventoryServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/dealership-inventories")
@RequiredArgsConstructor
@Validated
public class DealershipInventoryController {

  private final DealershipInventoryServiceImpl dealershipInventoryService;

  @PostMapping("/")
  public ResponseEntity<DealershipInventoryDto> createDealershipInventory(
      @Valid @RequestBody DealershipInventoryDto dealershipInventoryDTO) {
    DealershipInventoryDto createdDealershipInventory =
        dealershipInventoryService.createDealershipInventory(dealershipInventoryDTO);
    return new ResponseEntity<>(createdDealershipInventory, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DealershipInventoryDto> getDealershipInventoryById(
      @PathVariable @Min(1) Long id) {
    DealershipInventoryDto dealershipInventoryDTO =
        dealershipInventoryService.getDealershipInventoryById(id);
    return new ResponseEntity<>(dealershipInventoryDTO, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<List<DealershipInventoryDto>> getAllDealershipInventories() {
    List<DealershipInventoryDto> dealershipInventories =
        dealershipInventoryService.getAllDealershipInventories();
    return new ResponseEntity<>(dealershipInventories, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<DealershipInventoryDto> updateDealershipInventory(
      @PathVariable @Min(1) Long id,
      @Valid @RequestBody DealershipInventoryDto dealershipInventoryDTO) {
    DealershipInventoryDto updatedDealershipInventory =
        dealershipInventoryService.updateDealershipInventory(id, dealershipInventoryDTO);
    return new ResponseEntity<>(updatedDealershipInventory, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteDealershipInventory(@PathVariable @Min(1) Long id) {
    dealershipInventoryService.deleteDealershipInventory(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
