package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageResponseDto;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.impl.VehicleImageServiceImpl;
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
    @PreAuthorize("hasAuthority('vehicleImage:create')")
    public ResponseEntity<VehicleImageResponseDto> createVehicleImage(@Valid @RequestBody VehicleImageDto vehicleImageDTO) {
    
            VehicleImageResponseDto createdVehicleImage = vehicleImageService.createVehicleImage(vehicleImageDTO);
            return new ResponseEntity<>(createdVehicleImage, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleImage:read')")
    public ResponseEntity<VehicleImageResponseDto> getVehicleImageById(@PathVariable @Min(1) Long id) {
    
            VehicleImageResponseDto vehicleImageDTO = vehicleImageService.getVehicleImageById(id);
            return new ResponseEntity<>(vehicleImageDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleImage:read')")
    public ResponseEntity<List<VehicleImageResponseDto>> getAllVehicleImages() {
            List<VehicleImageResponseDto> vehicleImages = vehicleImageService.getAllVehicleImages();
            return new ResponseEntity<>(vehicleImages, HttpStatus.OK);
    }

    @GetMapping("/vehicle/{id}")
    @PreAuthorize("hasAuthority('vehicleImage:read')")
    public ResponseEntity<List<VehicleImageResponseDto>> getImagesForVehicle(@PathVariable @Min(1) Long id) {
            List<VehicleImageResponseDto> vehicleImages = vehicleImageService.getImagesForVehicle(id);
            return new ResponseEntity<>(vehicleImages, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleImage:update')")
    public ResponseEntity<VehicleImageResponseDto> updateVehicleImage(@PathVariable @Min(1) Long id,@Valid @RequestBody VehicleImageDto vehicleImageDTO) {
    
            VehicleImageResponseDto updatedVehicleImage = vehicleImageService.updateVehicleImage(id, vehicleImageDTO);
            return new ResponseEntity<>(updatedVehicleImage, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleImage:delete')")
    public ResponseEntity<Void> deleteVehicleImage(@PathVariable @Min(1) Long id) {
    
            vehicleImageService.deleteVehicleImage(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
