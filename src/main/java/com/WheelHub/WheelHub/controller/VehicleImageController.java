package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.VehicleImageDTO;
import com.WheelHub.WheelHub.service.impl.VehicleImageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vehicle-images")
@RequiredArgsConstructor
public class VehicleImageController {

    private final VehicleImageServiceImpl vehicleImageService;

    @PostMapping("/")
    public ResponseEntity<VehicleImageDTO> createVehicleImage(@RequestBody VehicleImageDTO vehicleImageDTO) {
        try {
            VehicleImageDTO createdVehicleImage = vehicleImageService.createVehicleImage(vehicleImageDTO);
            return new ResponseEntity<>(createdVehicleImage, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleImageDTO> getVehicleImageById(@PathVariable Long id) {
        try {
            VehicleImageDTO vehicleImageDTO = vehicleImageService.getVehicleImageById(id);
            return new ResponseEntity<>(vehicleImageDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VehicleImageDTO>> getAllVehicleImages() {
        try {
            List<VehicleImageDTO> vehicleImages = vehicleImageService.getAllVehicleImages();
            return new ResponseEntity<>(vehicleImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleImageDTO> updateVehicleImage(@PathVariable Long id, @RequestBody VehicleImageDTO vehicleImageDTO) {
        try {
            VehicleImageDTO updatedVehicleImage = vehicleImageService.updateVehicleImage(id, vehicleImageDTO);
            return new ResponseEntity<>(updatedVehicleImage, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleImage(@PathVariable Long id) {
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
