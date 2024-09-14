package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleDto;
import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleResponseDto;
import com.WheelHub.WheelHub.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDto createVehicle(VehicleDto vehicleDTO);

    VehicleResponseDto getVehicleById(Long id);

    List<VehicleResponseDto> getAllVehicles();

    VehicleDto updateVehicle(Long id, VehicleDto vehicleDTO);

    void deleteVehicle(Long id);

    Vehicle findById(Long id);
}
