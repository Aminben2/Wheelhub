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
        try {
            DealershipInventoryDto createdDealershipInventory = dealershipInventoryService.createDealershipInventory(dealershipInventoryDTO);
            return new ResponseEntity<>(createdDealershipInventory, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating dealership inventory: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealershipInventoryDto> getDealershipInventoryById(@PathVariable @Min(1) Long id) {
        try {
            DealershipInventoryDto dealershipInventoryDTO = dealershipInventoryService.getDealershipInventoryById(id);
            return new ResponseEntity<>(dealershipInventoryDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DealershipInventoryDto>> getAllDealershipInventories() {
        try {
            List<DealershipInventoryDto> dealershipInventories = dealershipInventoryService.getAllDealershipInventories();
            return new ResponseEntity<>(dealershipInventories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealershipInventoryDto> updateDealershipInventory(@PathVariable @Min(1) Long id, @Valid @RequestBody DealershipInventoryDto dealershipInventoryDTO) {
        try {
            DealershipInventoryDto updatedDealershipInventory = dealershipInventoryService.updateDealershipInventory(id, dealershipInventoryDTO);
            return new ResponseEntity<>(updatedDealershipInventory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealershipInventory(@PathVariable @Min(1) Long id) {
        try {
            dealershipInventoryService.deleteDealershipInventory(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
