package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
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
    public ResponseEntity<PriceDropDto> createPriceDrop(@RequestBody PriceDropDto priceDropDTO) {
        try {
            PriceDropDto createdPriceDrop = priceDropService.createPriceDrop(priceDropDTO);
            return new ResponseEntity<>(createdPriceDrop, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceDropDto> getPriceDropById(@PathVariable Long id) {
        try {
            PriceDropDto priceDropDTO = priceDropService.getPriceDropById(id);
            return new ResponseEntity<>(priceDropDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceDropDto>> getAllPriceDrops() {
        try {
            List<PriceDropDto> priceDrops = priceDropService.getAllPriceDrops();
            return new ResponseEntity<>(priceDrops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceDropDto> updatePriceDrop(@PathVariable Long id, @RequestBody PriceDropDto priceDropDTO) {
        try {
            PriceDropDto updatedPriceDrop = priceDropService.updatePriceDrop(id, priceDropDTO);
            return new ResponseEntity<>(updatedPriceDrop, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceDrop(@PathVariable Long id) {
        try {
            priceDropService.deletePriceDrop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
