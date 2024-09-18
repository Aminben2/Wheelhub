package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureResponseDto;
import com.WheelHub.WheelHub.service.VehicleFeatureService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicle-features")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('ADMIN','SELLER','BUYER')")
public class VehicleFeatureController {

  private final VehicleFeatureService vehicleFeatureService;

  @PostMapping("/")
  @PreAuthorize("hasAuthority('vehicleFeature:create')")
  public ResponseEntity<VehicleFeatureResponseDto> createVehicleFeature(
      @Valid @RequestBody VehicleFeatureDto vehicleFeatureDto) {
    VehicleFeatureResponseDto createdVehicleFeature = vehicleFeatureService.save(vehicleFeatureDto);
    return new ResponseEntity<>(createdVehicleFeature, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('vehicleFeature:read')")
  public ResponseEntity<VehicleFeatureResponseDto> getVehicleFeatureById(
      @PathVariable @Min(1) Long id) {

    VehicleFeatureResponseDto vehicleFeatureDto = vehicleFeatureService.findById(id);
    return new ResponseEntity<>(vehicleFeatureDto, HttpStatus.OK);
  }

  @GetMapping("/all")
  @PreAuthorize("hasAuthority('vehicleFeature:read')")
  public ResponseEntity<List<VehicleFeatureResponseDto>> getAllVehicleFeatures() {
    List<VehicleFeatureResponseDto> vehicleFeatures = vehicleFeatureService.findAll();
    return new ResponseEntity<>(vehicleFeatures, HttpStatus.OK);
  }

  @GetMapping("/vehicle/{id}")
  @PreAuthorize("hasAuthority('vehicleFeature:read')")
  public ResponseEntity<List<VehicleFeatureResponseDto>> getVehicleFeatures(
      @PathVariable("id") @Min(1) Long id) {
    List<VehicleFeatureResponseDto> vehicleFeatures =
        vehicleFeatureService.findFeaturesForVehicle(id);
    return new ResponseEntity<>(vehicleFeatures, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAuthority('vehicleFeature:update')")
  public ResponseEntity<VehicleFeatureResponseDto> updateVehicleFeature(
      @PathVariable @Min(1) Long id, @Valid @RequestBody VehicleFeatureDto vehicleFeatureDto) {

    VehicleFeatureResponseDto updatedVehicleFeature =
        vehicleFeatureService.update(id, vehicleFeatureDto);
    return new ResponseEntity<>(updatedVehicleFeature, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('vehicleFeature:delete')")
  public ResponseEntity<Void> deleteVehicleFeature(@PathVariable @Min(1) Long id) {

    vehicleFeatureService.deleteById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
