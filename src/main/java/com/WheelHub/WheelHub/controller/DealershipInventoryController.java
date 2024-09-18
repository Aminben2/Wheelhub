package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.DealershipInventoryDtos.DealershipInventoryDto;
import com.WheelHub.WheelHub.service.impl.DealershipInventoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/dealership-inventories")
@RequiredArgsConstructor
@Validated
public class DealershipInventoryController {

    private final DealershipInventoryServiceImpl dealershipInventoryService;

    @PostMapping("/")
    public ResponseEntity<DealershipInventoryDto> createDealershipInventory(@Valid @RequestBody DealershipInventoryDto dealershipInventoryDTO) {
        DealershipInventoryDto createdDealershipInventory = dealershipInventoryService.createDealershipInventory(dealershipInventoryDTO);
        return new ResponseEntity<>(createdDealershipInventory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealershipInventoryDto> getDealershipInventoryById(@PathVariable @Min(1) Long id) {
        try {
            DealershipInventoryDto dealershipInventoryDTO = dealershipInventoryService.getDealershipInventoryById(id);
            return new ResponseEntity<>(dealershipInventoryDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DealershipInventoryDto>> getAllDealershipInventories() {
        List<DealershipInventoryDto> dealershipInventories = dealershipInventoryService.getAllDealershipInventories();
        return new ResponseEntity<>(dealershipInventories, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealershipInventoryDto> updateDealershipInventory(@PathVariable @Min(1) Long id, @Valid @RequestBody DealershipInventoryDto dealershipInventoryDTO) {
        try {
            DealershipInventoryDto updatedDealershipInventory = dealershipInventoryService.updateDealershipInventory(id, dealershipInventoryDTO);
            return new ResponseEntity<>(updatedDealershipInventory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealershipInventory(@PathVariable @Min(1) Long id) {
        try {
            dealershipInventoryService.deleteDealershipInventory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
