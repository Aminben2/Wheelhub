package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehicleImageService {
    VehicleImageResponseDto createVehicleImage(VehicleImageDto vehicleImageDTO);

    VehicleImageResponseDto getVehicleImageById(Long id);

    List<VehicleImageResponseDto> getImagesForVehicle(Long id);

    List<VehicleImageResponseDto> getAllVehicleImages();

    VehicleImageResponseDto updateVehicleImage(Long id, VehicleImageDto vehicleImageDTO);

    void deleteVehicleImage(Long id);
}
