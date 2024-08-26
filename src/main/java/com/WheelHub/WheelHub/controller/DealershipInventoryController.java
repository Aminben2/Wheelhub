package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.DealershipInventoryDTO;
import com.WheelHub.WheelHub.service.impl.DealershipInventoryServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/dealership-inventories")
@RequiredArgsConstructor
public class DealershipInventoryController {

    private final DealershipInventoryServiceImpl dealershipInventoryService;

    @PostMapping("/")
    public ResponseEntity<DealershipInventoryDTO> createDealershipInventory(@RequestBody DealershipInventoryDTO dealershipInventoryDTO) {
        try {
            DealershipInventoryDTO createdDealershipInventory = dealershipInventoryService.createDealershipInventory(dealershipInventoryDTO);
            return new ResponseEntity<>(createdDealershipInventory, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating dealership inventory: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealershipInventoryDTO> getDealershipInventoryById(@PathVariable Long id) {
        try {
            DealershipInventoryDTO dealershipInventoryDTO = dealershipInventoryService.getDealershipInventoryById(id);
            return new ResponseEntity<>(dealershipInventoryDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DealershipInventoryDTO>> getAllDealershipInventories() {
        try {
            List<DealershipInventoryDTO> dealershipInventories = dealershipInventoryService.getAllDealershipInventories();
            return new ResponseEntity<>(dealershipInventories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealershipInventoryDTO> updateDealershipInventory(@PathVariable Long id, @RequestBody DealershipInventoryDTO dealershipInventoryDTO) {
        try {
            DealershipInventoryDTO updatedDealershipInventory = dealershipInventoryService.updateDealershipInventory(id, dealershipInventoryDTO);
            return new ResponseEntity<>(updatedDealershipInventory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealershipInventory(@PathVariable Long id) {
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
