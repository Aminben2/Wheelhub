package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageResponseDto;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.impl.VehicleImageServiceImpl;
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

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/vehicle-images")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
public class VehicleImageController {

    private final VehicleImageServiceImpl vehicleImageService;
    private final VehicleRepository vehicleRepository;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('vehicleFeature:create')")
    public ResponseEntity<VehicleImageResponseDto> createVehicleImage(@Valid @RequestBody VehicleImageDto vehicleImageDTO) {
        try {
            VehicleImageResponseDto createdVehicleImage = vehicleImageService.createVehicleImage(vehicleImageDTO);
            return new ResponseEntity<>(createdVehicleImage, HttpStatus.CREATED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:read')")
    public ResponseEntity<VehicleImageResponseDto> getVehicleImageById(@PathVariable @Min(1) Long id) {
        try {
            VehicleImageResponseDto vehicleImageDTO = vehicleImageService.getVehicleImageById(id);
            return new ResponseEntity<>(vehicleImageDTO, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleFeature:read')")
    public ResponseEntity<List<VehicleImageResponseDto>> getAllVehicleImages() {
        try {
            List<VehicleImageResponseDto> vehicleImages = vehicleImageService.getAllVehicleImages();
            return new ResponseEntity<>(vehicleImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehicle/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:read')")
    public ResponseEntity<List<VehicleImageResponseDto>> getImagesForVehicle(@PathVariable @Min(1) Long id) {
        try {
            List<VehicleImageResponseDto> vehicleImages = vehicleImageService.getImagesForVehicle(id);
            return new ResponseEntity<>(vehicleImages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:update')")
    public ResponseEntity<VehicleImageResponseDto> updateVehicleImage(@PathVariable @Min(1) Long id,@Valid @RequestBody VehicleImageDto vehicleImageDTO) {
        try {
            VehicleImageResponseDto updatedVehicleImage = vehicleImageService.updateVehicleImage(id, vehicleImageDTO);
            return new ResponseEntity<>(updatedVehicleImage, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:delete')")
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
