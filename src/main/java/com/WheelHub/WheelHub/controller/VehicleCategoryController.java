package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;
import com.WheelHub.WheelHub.service.VehicleCategoryService;
import jakarta.persistence.EntityNotFoundException;
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
    public ResponseEntity<VehicleCategoryDto> createVehicleCategory(@Valid @RequestBody VehicleCategoryDto vehicleCategoryDto) {
        try {
            VehicleCategoryDto createdVehicleCategory = vehicleCategoryService.save(vehicleCategoryDto);
            return new ResponseEntity<>(createdVehicleCategory, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleCategory:read')")
    public ResponseEntity<VehicleCategoryDto> getVehicleCategoryById(@PathVariable @Min(1) Long id) {
        try {
            VehicleCategoryDto vehicleCategoryDto = vehicleCategoryService.findById(id);
            return new ResponseEntity<>(vehicleCategoryDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleCategory:read')")
    public ResponseEntity<List<VehicleCategoryDto>> getAllVehicleCategories() {
        try {
            List<VehicleCategoryDto> vehicleCategories = vehicleCategoryService.findAll();
            return new ResponseEntity<>(vehicleCategories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleCategory:update')")
    public ResponseEntity<VehicleCategoryDto> updateVehicleCategory(@PathVariable @Min(1) Long id, @Valid @RequestBody VehicleCategoryDto vehicleCategoryDto) {
        try {
            VehicleCategoryDto updatedVehicleCategory = vehicleCategoryService.update(id, vehicleCategoryDto);
            return new ResponseEntity<>(updatedVehicleCategory, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleCategory:delete')")
    public ResponseEntity<Void> deleteVehicleCategory(@PathVariable @Min(1) Long id) {
        try {
            vehicleCategoryService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
