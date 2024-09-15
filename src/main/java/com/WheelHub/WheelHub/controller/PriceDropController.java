package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropResponseDto;
import com.WheelHub.WheelHub.service.impl.PriceDropServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
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
@Valid
public class PriceDropController {

    private final PriceDropServiceImpl priceDropService;

    @PostMapping("/")
    public ResponseEntity<PriceDropResponseDto> createPriceDrop(@Valid @RequestBody PriceDropDto priceDropDTO) {
        try {
            PriceDropResponseDto createdPriceDrop = priceDropService.createPriceDrop(priceDropDTO);
            return new ResponseEntity<>(createdPriceDrop, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceDropResponseDto> getPriceDropById(@PathVariable @Min(1) Long id) {
        try {
            PriceDropResponseDto priceDropDTO = priceDropService.getPriceDropById(id);
            return new ResponseEntity<>(priceDropDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/latest/vehicle/{id}")
    public ResponseEntity<PriceDropResponseDto> getLatestPriceDropForVehicle(@PathVariable @Min(1) Long id) {
        try {
            PriceDropResponseDto priceDropDTO = priceDropService.getLatestPriceDropByVehicleId(id);
            return new ResponseEntity<>(priceDropDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<PriceDropResponseDto>> getAllPriceDrops() {
        try {
            List<PriceDropResponseDto> priceDrops = priceDropService.getAllPriceDrops();
            return new ResponseEntity<>(priceDrops, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PriceDropResponseDto> updatePriceDrop(@PathVariable @Min(1) Long id,@Valid @RequestBody PriceDropDto priceDropDTO) {
        try {
            PriceDropResponseDto updatedPriceDrop = priceDropService.updatePriceDrop(id, priceDropDTO);
            return new ResponseEntity<>(updatedPriceDrop, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriceDrop(@PathVariable @Min(1) Long id) {
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
