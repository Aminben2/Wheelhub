package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;
import com.WheelHub.WheelHub.service.VehicleTypeService;
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
@RequestMapping("/api/vehicle-types")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
public class VehicleTypeController {

    private final VehicleTypeService vehicleTypeService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('vehicleType:create')")
    public ResponseEntity<VehicleTypeDto> createVehicleType(@Valid @RequestBody VehicleTypeDto vehicleTypeDto) {
        try {
            VehicleTypeDto createdVehicleType = vehicleTypeService.save(vehicleTypeDto);
            return new ResponseEntity<>(createdVehicleType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleType:read')")
    public ResponseEntity<VehicleTypeDto> getVehicleTypeById(@PathVariable @Min(1) Long id) {
        try {
            VehicleTypeDto vehicleTypeDto = vehicleTypeService.findById(id);
            return new ResponseEntity<>(vehicleTypeDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleType:read')")
    public ResponseEntity<List<VehicleTypeDto>> getAllVehicleTypes() {
        try {
            List<VehicleTypeDto> vehicleTypes = vehicleTypeService.findAll();
            return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleType:update')")
    public ResponseEntity<VehicleTypeDto> updateVehicleType(@PathVariable @Min(1) Long id, @Valid @RequestBody VehicleTypeDto vehicleTypeDto) {
        try {
            VehicleTypeDto updatedVehicleType = vehicleTypeService.update(id, vehicleTypeDto);
            return new ResponseEntity<>(updatedVehicleType, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleType:delete')")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable @Min(1) Long id) {
        try {
            vehicleTypeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
