package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;
import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeResponseDto;
import com.WheelHub.WheelHub.service.VehicleTypeService;
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
    public ResponseEntity<VehicleTypeResponseDto> createVehicleType(@Valid @RequestBody VehicleTypeDto vehicleTypeDto) {
            VehicleTypeResponseDto createdVehicleType = vehicleTypeService.save(vehicleTypeDto);
            return new ResponseEntity<>(createdVehicleType, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleType:read')")
    public ResponseEntity<VehicleTypeResponseDto> getVehicleTypeById(@PathVariable @Min(1) Long id) {
     
            VehicleTypeResponseDto vehicleTypeDto = vehicleTypeService.findById(id);
            return new ResponseEntity<>(vehicleTypeDto, HttpStatus.OK);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleType:read')")
    public ResponseEntity<List<VehicleTypeResponseDto>> getAllVehicleTypes() {
            List<VehicleTypeResponseDto> vehicleTypes = vehicleTypeService.findAll();
            return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleType:update')")
    public ResponseEntity<VehicleTypeResponseDto> updateVehicleType(@PathVariable @Min(1) Long id, @Valid @RequestBody VehicleTypeDto vehicleTypeDto) {
     
            VehicleTypeResponseDto updatedVehicleType = vehicleTypeService.update(id, vehicleTypeDto);
            return new ResponseEntity<>(updatedVehicleType, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleType:delete')")
    public ResponseEntity<Void> deleteVehicleType(@PathVariable @Min(1) Long id) {
     
            vehicleTypeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
