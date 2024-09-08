package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;

import java.util.List;

public interface VehicleCategoryService {
    VehicleCategoryDto save(VehicleCategoryDto vehicleCategoryDto);
    VehicleCategoryDto update(Long id, VehicleCategoryDto vehicleCategoryDto);
    VehicleCategoryDto findById(Long id);
    List<VehicleCategoryDto> findAll();
    void deleteById(Long id);
}
