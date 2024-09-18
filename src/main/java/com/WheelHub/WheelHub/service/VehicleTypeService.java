package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;
import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeResponseDto;
import java.util.List;

public interface VehicleTypeService {
  VehicleTypeResponseDto save(VehicleTypeDto vehicleTypeDto);

  VehicleTypeResponseDto update(Long id, VehicleTypeDto vehicleTypeDto);

  VehicleTypeResponseDto findById(Long id);

  List<VehicleTypeResponseDto> findAll();

  void deleteById(Long id);
}
