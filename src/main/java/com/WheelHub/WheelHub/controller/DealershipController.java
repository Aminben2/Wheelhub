package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.DealershipDTO;
import com.WheelHub.WheelHub.service.impl.DealershipServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/dealerships")
@RequiredArgsConstructor
public class DealershipController {

    private final DealershipServiceImpl dealershipService;

    @PostMapping("/")
    public ResponseEntity<DealershipDTO> createDealership(@RequestBody DealershipDTO dealershipDTO) {
        try {
            DealershipDTO createdDealership = dealershipService.createDealership(dealershipDTO);
            return new ResponseEntity<>(createdDealership, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating dealership: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealershipDTO> getDealershipById(@PathVariable Long id) {
        try {
            DealershipDTO dealershipDTO = dealershipService.getDealershipById(id);
            return new ResponseEntity<>(dealershipDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("Dealership not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DealershipDTO>> getAllDealerships() {
        try {
            List<DealershipDTO> dealerships = dealershipService.getAllDealerships();
            return new ResponseEntity<>(dealerships, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealershipDTO> updateDealership(@PathVariable Long id, @RequestBody DealershipDTO dealershipDTO) {
        try {
            DealershipDTO updatedDealership = dealershipService.updateDealership(id, dealershipDTO);
            return new ResponseEntity<>(updatedDealership, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("Dealership not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable Long id) {
        try {
            dealershipService.deleteDealership(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            log.error("Dealership not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
