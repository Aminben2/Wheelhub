package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleDto;
import com.WheelHub.WheelHub.dto.vehicleDtos.VehicleResponseDto;
import com.WheelHub.WheelHub.entity.Vehicle;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface VehicleService {

  VehicleDto createVehicle(VehicleDto vehicleDTO);

  VehicleResponseDto getVehicleById(Long id);

  List<VehicleResponseDto> getAllVehicles();

  VehicleDto updateVehicle(Long id, VehicleDto vehicleDTO);

  void deleteVehicle(Long id);

  Vehicle findById(Long id);

  List<String> saveImages(MultipartFile[] imageFiles, Long vehicleId);
}
