package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;
import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryResponseDto;
import com.WheelHub.WheelHub.service.VehicleCategoryService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicle-categories")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
public class VehicleCategoryController {

    private final VehicleCategoryService vehicleCategoryService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('vehicleCategory:create')")
    public ResponseEntity<VehicleCategoryResponseDto> createVehicleCategory(@Valid @RequestBody VehicleCategoryDto vehicleCategoryDto) {
            VehicleCategoryResponseDto createdVehicleCategory = vehicleCategoryService.save(vehicleCategoryDto);
            return new ResponseEntity<>(createdVehicleCategory, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleCategory:read')")
    public ResponseEntity<VehicleCategoryResponseDto> getVehicleCategoryById(@PathVariable @Min(1) Long id) {
    
            VehicleCategoryResponseDto vehicleCategoryDto = vehicleCategoryService.findById(id);
            return new ResponseEntity<>(vehicleCategoryDto, HttpStatus.OK);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleCategory:read')")
    public ResponseEntity<List<VehicleCategoryResponseDto>> getAllVehicleCategories() {
            List<VehicleCategoryResponseDto> vehicleCategories = vehicleCategoryService.findAll();
            return new ResponseEntity<>(vehicleCategories, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleCategory:update')")
    public ResponseEntity<VehicleCategoryResponseDto> updateVehicleCategory(@PathVariable @Min(1) Long id, @Valid @RequestBody VehicleCategoryDto vehicleCategoryDto) {
    
            VehicleCategoryResponseDto updatedVehicleCategory = vehicleCategoryService.update(id, vehicleCategoryDto);
            return new ResponseEntity<>(updatedVehicleCategory, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleCategory:delete')")
    public ResponseEntity<Void> deleteVehicleCategory(@PathVariable @Min(1) Long id) {
    
            vehicleCategoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
