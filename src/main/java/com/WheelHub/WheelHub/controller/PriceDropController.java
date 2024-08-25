package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.PriceDropDTO;
import com.WheelHub.WheelHub.service.impl.PriceDropServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/price-drops")
@RequiredArgsConstructor
public class PriceDropController {

    private final PriceDropServiceImpl priceDropService;

    @PostMapping("/")
    public ResponseEntity<PriceDropDTO> createPriceDrop(@RequestBody PriceDropDTO priceDropDTO) {
        try {
            PriceDropDTO createdPriceDrop = priceDropService.createPriceDrop(priceDropDTO);
            return new ResponseEntity<>(createdPriceDrop, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            log.error("Error creating price drop: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceDropDTO> getPriceDropById(@PathVariable Long id) {
        try {
            PriceDropDTO priceDropDTO = priceDropService.getPriceDropById(id);
            return new ResponseEntity<>(priceDropDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("PriceDrop not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceDropDTO>> getAllPriceDrops() {
        try {
            List<PriceDropDTO> priceDrops = priceDropService.getAllPriceDrops();
            return new ResponseEntity<>(priceDrops, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceDropDTO> updatePriceDrop(@PathVariable Long id, @RequestBody PriceDropDTO priceDropDTO) {
        try {
            PriceDropDTO updatedPriceDrop = priceDropService.updatePriceDrop(id, priceDropDTO);
            return new ResponseEntity<>(updatedPriceDrop, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            log.error("PriceDrop not found: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceDrop(@PathVariable Long id) {
        try {
            priceDropService.deletePriceDrop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            log.error("PriceDrop not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
