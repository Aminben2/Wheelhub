package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.VehicleDTO;
import com.WheelHub.WheelHub.entity.Vehicle;

import java.util.List;

public interface VehicleService {

    VehicleDTO createVehicle(VehicleDTO vehicleDTO);

    VehicleDTO getVehicleById(Long id);

    List<VehicleDTO> getAllVehicles();

    VehicleDTO updateVehicle(Long id, VehicleDTO vehicleDTO);

    void deleteVehicle(Long id);
    Vehicle findById(Long id);

}
