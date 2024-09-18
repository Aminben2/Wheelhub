package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;
import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryResponseDto;
import java.util.List;

public interface VehicleCategoryService {
  VehicleCategoryResponseDto save(VehicleCategoryDto vehicleCategoryDto);

  VehicleCategoryResponseDto update(Long id, VehicleCategoryDto vehicleCategoryDto);

  VehicleCategoryResponseDto findById(Long id);

  List<VehicleCategoryResponseDto> findAll();

  void deleteById(Long id);
}
