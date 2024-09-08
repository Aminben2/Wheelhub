package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;

import java.util.List;

public interface VehicleFeatureService {
    VehicleFeatureDto save(VehicleFeatureDto vehicleFeatureDto);
    VehicleFeatureDto update(Long id, VehicleFeatureDto vehicleFeatureDto);
    VehicleFeatureDto findById(Long id);
    List<VehicleFeatureDto> findAll();
    void deleteById(Long id);
}

