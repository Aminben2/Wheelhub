package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureResponseDto;
import com.WheelHub.WheelHub.service.VehicleFeatureService;
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
@RequestMapping("/api/vehicle-features")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
public class VehicleFeatureController {

    private final VehicleFeatureService vehicleFeatureService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('vehicleFeature:create')")
    public ResponseEntity<VehicleFeatureResponseDto> createVehicleFeature(@Valid @RequestBody VehicleFeatureDto vehicleFeatureDto) {
        try {
            VehicleFeatureResponseDto createdVehicleFeature = vehicleFeatureService.save(vehicleFeatureDto);
            return new ResponseEntity<>(createdVehicleFeature, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:read')")
    public ResponseEntity<VehicleFeatureResponseDto> getVehicleFeatureById(@PathVariable @Min(1) Long id) {
        try {
            VehicleFeatureResponseDto vehicleFeatureDto = vehicleFeatureService.findById(id);
            return new ResponseEntity<>(vehicleFeatureDto, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('vehicleFeature:read')")
    public ResponseEntity<List<VehicleFeatureResponseDto>> getAllVehicleFeatures() {
        try {
            List<VehicleFeatureResponseDto> vehicleFeatures = vehicleFeatureService.findAll();
            return new ResponseEntity<>(vehicleFeatures, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/vehicle/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:read')")
    public ResponseEntity<List<VehicleFeatureResponseDto>> getVehicleFeatures(@PathVariable("id") @Min(1) Long id) {
        try {
            List<VehicleFeatureResponseDto> vehicleFeatures = vehicleFeatureService.findFeaturesForVehicle(id);
            return new ResponseEntity<>(vehicleFeatures, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:update')")
    public ResponseEntity<VehicleFeatureResponseDto> updateVehicleFeature(@PathVariable @Min(1) Long id, @Valid @RequestBody VehicleFeatureDto vehicleFeatureDto) {
        try {
            VehicleFeatureResponseDto updatedVehicleFeature = vehicleFeatureService.update(id, vehicleFeatureDto);
            return new ResponseEntity<>(updatedVehicleFeature, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('vehicleFeature:delete')")
    public ResponseEntity<Void> deleteVehicleFeature(@PathVariable @Min(1) Long id) {
        try {
            vehicleFeatureService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
