package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.service.impl.VehicleImageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vehicle-images")
@RequiredArgsConstructor
@Validated
public class VehicleImageController {

    private final VehicleImageServiceImpl vehicleImageService;

    @PostMapping("/")
    public ResponseEntity<VehicleImageDto> createVehicleImage(@Valid @RequestBody VehicleImageDto vehicleImageDTO) {
        try {
            VehicleImageDto createdVehicleImage = vehicleImageService.createVehicleImage(vehicleImageDTO);
            return new ResponseEntity<>(createdVehicleImage, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleImageDto> getVehicleImageById(@PathVariable @Min(1) Long id) {
        try {
            VehicleImageDto vehicleImageDTO = vehicleImageService.getVehicleImageById(id);
            return new ResponseEntity<>(vehicleImageDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleImageDto>> getAllVehicleImages() {
        try {
            List<VehicleImageDto> vehicleImages = vehicleImageService.getAllVehicleImages();
            return new ResponseEntity<>(vehicleImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleImageDto> updateVehicleImage(@PathVariable @Min(1) Long id,@Valid @RequestBody VehicleImageDto vehicleImageDTO) {
        try {
            VehicleImageDto updatedVehicleImage = vehicleImageService.updateVehicleImage(id, vehicleImageDTO);
            return new ResponseEntity<>(updatedVehicleImage, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleImage(@PathVariable @Min(1) Long id) {
        try {
            vehicleImageService.deleteVehicleImage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
