package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleDto;
import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleResponseDto;
import com.WheelHub.WheelHub.service.impl.VehicleServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
public class VehicleController {

    private final VehicleServiceImpl vehicleService;

    @PostMapping("/{vehicleId}/upload-images")
    @PreAuthorize("hasAuthority('vehicles:imageUpload')")
    public ResponseEntity<List<String>> uploadVehicleImages(
            @PathVariable Long vehicleId,
            @RequestParam("images") MultipartFile[] imageFiles) {
        List<String> imageUrls = vehicleService.saveImages(imageFiles, vehicleId);
        return ResponseEntity.ok(imageUrls);
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('vehicles:create')")
    public ResponseEntity<VehicleDto> createVehicle(@Valid @RequestBody VehicleDto vehicleDTO) {
        try {
            VehicleDto createdVehicle = vehicleService.createVehicle(vehicleDTO);
            return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable @Min(1) Long id) {
        try {
            VehicleResponseDto vehicleDTO = vehicleService.getVehicleById(id);
            return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        try {
            List<VehicleResponseDto> vehicles = vehicleService.getAllVehicles();
            return new ResponseEntity<>(vehicles, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicles:update')")
    public ResponseEntity<VehicleDto> updateVehicle(@PathVariable @Min(1) Long id, @Valid @RequestBody VehicleDto vehicleDTO) {
        try {
            VehicleDto updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
            return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicles:delete')")
    public ResponseEntity<Void> deleteVehicle(@PathVariable @Min(1) Long id) {
        try {
            vehicleService.deleteVehicle(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

