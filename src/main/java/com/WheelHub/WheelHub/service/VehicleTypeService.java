package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;

import java.util.List;

public interface VehicleTypeService {
    VehicleTypeDto save(VehicleTypeDto vehicleTypeDto);
    VehicleTypeDto update(Long id, VehicleTypeDto vehicleTypeDto);
    VehicleTypeDto findById(Long id);
    List<VehicleTypeDto> findAll();
    void deleteById(Long id);
}

