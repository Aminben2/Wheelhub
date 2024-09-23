package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleDto;
import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleResponseDto;
import com.WheelHub.WheelHub.service.impl.VehicleServiceImpl;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/{vehicleId}/upload")
    @PreAuthorize("hasAuthority('vehicles:imageUpload')")
    public ResponseEntity<List<String>> uploadVehicleImages(
            @PathVariable("vehicleId") Long vehicleId,
            @RequestParam("images") MultipartFile[] imageFiles) {
        List<String> imageUrls = vehicleService.saveImages(imageFiles, vehicleId);
        return ResponseEntity.ok(imageUrls);
    }

    @PostMapping("/{vehicleId}/uploadCloud")
    public ResponseEntity<List<String>> uploadVehicleImages(
            @PathVariable Long vehicleId, @RequestParam("images") List<MultipartFile> files) {
        try {
            List<String> imageUrls = vehicleService.uploadVehicleImages(vehicleId, files);
            return ResponseEntity.ok(imageUrls);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/")
    @PreAuthorize("hasAuthority('vehicles:create')")
    public ResponseEntity<VehicleResponseDto> createVehicle(
            @Valid @RequestBody VehicleDto vehicleDTO) {

        VehicleResponseDto createdVehicle = vehicleService.createVehicle(vehicleDTO);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public ResponseEntity<VehicleResponseDto> getVehicleById(@PathVariable @Min(1) Long id) {

        VehicleResponseDto vehicleDTO = vehicleService.getVehicleById(id);
        return new ResponseEntity<>(vehicleDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public ResponseEntity<List<VehicleResponseDto>> getAllVehicles() {
        List<VehicleResponseDto> vehicles = vehicleService.getAllVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/allDeleted")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public ResponseEntity<List<VehicleResponseDto>> getAllDeletedVehicles() {
        List<VehicleResponseDto> vehicles = vehicleService.getAllDeletedVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @GetMapping("/allNotDeleted")
    @PreAuthorize("hasAuthority('vehicles:read')")
    public ResponseEntity<List<VehicleResponseDto>> getAllNotDeletedVehicles() {
        List<VehicleResponseDto> vehicles = vehicleService.getAllNotDeletedVehicles();
        return new ResponseEntity<>(vehicles, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicles:update')")
    public ResponseEntity<VehicleResponseDto> updateVehicle(
            @PathVariable @Min(1) Long id, @Valid @RequestBody VehicleDto vehicleDTO) {

        VehicleResponseDto updatedVehicle = vehicleService.updateVehicle(id, vehicleDTO);
        return new ResponseEntity<>(updatedVehicle, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicles:delete')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteVehicle(@PathVariable @Min(1) Long id) {

        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/deleteAllSelected")
    @PreAuthorize("hasAuthority('vehicles:deleteAllSelected')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> deleteAllSelectedVehicles(@RequestBody List<Long> vehiclesIds) {
        vehicleService.deleteAllVehiclesByIds(vehiclesIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/softDeleteAllSelected")
    @PreAuthorize("hasAuthority('vehicles:softDeleteAllSelected')")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> softDeleteAllSelectedVehicles(@RequestBody List<Long> vehiclesIds) {
        vehicleService.softDeleteAllVehiclesByIds(vehiclesIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
