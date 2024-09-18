package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropDto;
import com.WheelHub.WheelHub.dto.priceDropDtos.PriceDropResponseDto;
import com.WheelHub.WheelHub.service.impl.PriceDropServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/price-drops")
@RequiredArgsConstructor
@Valid
@PreAuthorize("hasAnyRole('ADMIN','BUYER','SELLER')")
public class PriceDropController {

    private final PriceDropServiceImpl priceDropService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('priceDrop:create')")
    public ResponseEntity<PriceDropResponseDto> createPriceDrop(@Valid @RequestBody PriceDropDto priceDropDTO) {
     
            PriceDropResponseDto createdPriceDrop = priceDropService.createPriceDrop(priceDropDTO);
            return new ResponseEntity<>(createdPriceDrop, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('priceDrop:read')")
    public ResponseEntity<PriceDropResponseDto> getPriceDropById(@PathVariable @Min(1) Long id) {
     
            PriceDropResponseDto priceDropDTO = priceDropService.getPriceDropById(id);
            return new ResponseEntity<>(priceDropDTO, HttpStatus.OK);

    }

    @GetMapping("/latest/vehicle/{id}")
    @PreAuthorize("hasAuthority('priceDrop:read')")
    public ResponseEntity<PriceDropResponseDto> getLatestPriceDropForVehicle(@PathVariable @Min(1) Long id) {
     
            PriceDropResponseDto priceDropDTO = priceDropService.getLatestPriceDropByVehicleId(id);
            return new ResponseEntity<>(priceDropDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('priceDrop:read')")
    public ResponseEntity<List<PriceDropResponseDto>> getAllPriceDrops() {
            List<PriceDropResponseDto> priceDrops = priceDropService.getAllPriceDrops();
            return new ResponseEntity<>(priceDrops, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('priceDrop:update')")
    public ResponseEntity<PriceDropResponseDto> updatePriceDrop(@PathVariable @Min(1) Long id,@Valid @RequestBody PriceDropDto priceDropDTO) {
     
            PriceDropResponseDto updatedPriceDrop = priceDropService.updatePriceDrop(id, priceDropDTO);
            return new ResponseEntity<>(updatedPriceDrop, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('priceDrop:delete')")
    public ResponseEntity<Void> deletePriceDrop(@PathVariable @Min(1) Long id) {
     
            priceDropService.deletePriceDrop(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
