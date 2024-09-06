package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.dealershipDtos.DealershipDto;
import com.WheelHub.WheelHub.service.impl.DealershipServiceImpl;
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
@RequestMapping("/api/dealerships")
@RequiredArgsConstructor
@Validated
public class DealershipController {

    private final DealershipServiceImpl dealershipService;

    @PostMapping("/")
    public ResponseEntity<DealershipDto> createDealership(
            @Valid @RequestBody DealershipDto dealershipDTO) {
        try {
            DealershipDto createdDealership = dealershipService.createDealership(dealershipDTO);
            return new ResponseEntity<>(createdDealership, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating dealership: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DealershipDto> getDealershipById(@PathVariable @Min(1) Long id) {
        try {
            DealershipDto dealershipDTO = dealershipService.getDealershipById(id);
            return new ResponseEntity<>(dealershipDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DealershipDto>> getAllDealerships() {
        try {
            List<DealershipDto> dealerships = dealershipService.getAllDealerships();
            return new ResponseEntity<>(dealerships, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<DealershipDto> updateDealership(@PathVariable @Min(1) Long id, @Valid @RequestBody DealershipDto dealershipDTO) {
        try {
            DealershipDto updatedDealership = dealershipService.updateDealership(id, dealershipDTO);
            return new ResponseEntity<>(updatedDealership, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealership(@PathVariable @Min(1) Long id) {
        try {
            dealershipService.deleteDealership(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
