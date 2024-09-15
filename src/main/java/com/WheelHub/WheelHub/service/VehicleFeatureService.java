package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureResponseDto;

import java.util.List;

public interface VehicleFeatureService {
    VehicleFeatureResponseDto save(VehicleFeatureDto vehicleFeatureDto);
    VehicleFeatureResponseDto update(Long id, VehicleFeatureDto vehicleFeatureDto);
    VehicleFeatureResponseDto findById(Long id);
    List<VehicleFeatureResponseDto> findAll();
    List<VehicleFeatureResponseDto> findFeaturesForVehicle(Long id);
    void deleteById(Long id);
}

