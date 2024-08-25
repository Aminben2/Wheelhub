package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.VehicleImageDTO;

import java.util.List;

public interface VehicleImageService {
    VehicleImageDTO createVehicleImage(VehicleImageDTO vehicleImageDTO);

    VehicleImageDTO getVehicleImageById(Long id);

    List<VehicleImageDTO> getAllVehicleImages();

    VehicleImageDTO updateVehicleImage(Long id, VehicleImageDTO vehicleImageDTO);

    void deleteVehicleImage(Long id);
}
