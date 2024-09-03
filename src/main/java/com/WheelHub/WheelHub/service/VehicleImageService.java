package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;

import java.util.List;

public interface VehicleImageService {
    VehicleImageDto createVehicleImage(VehicleImageDto vehicleImageDTO);

    VehicleImageDto getVehicleImageById(Long id);

    List<VehicleImageDto> getAllVehicleImages();

    VehicleImageDto updateVehicleImage(Long id, VehicleImageDto vehicleImageDTO);

    void deleteVehicleImage(Long id);
}
