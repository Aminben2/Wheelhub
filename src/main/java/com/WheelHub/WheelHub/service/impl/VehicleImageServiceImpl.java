package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.entity.VehicleImage;
import com.WheelHub.WheelHub.mapper.VehicleImageMapper;
import com.WheelHub.WheelHub.repository.VehicleImageRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.VehicleImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleImageServiceImpl implements VehicleImageService {

    private final VehicleImageRepository vehicleImageRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public VehicleImageDto createVehicleImage(VehicleImageDto vehicleImageDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleImageDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + vehicleImageDTO.getVehicleId()));

        VehicleImage vehicleImage = VehicleImageMapper.dtoToEntity(vehicleImageDTO);
        vehicleImage.setVehicle(vehicle);

        vehicleImage = vehicleImageRepository.save(vehicleImage);
        return VehicleImageMapper.entityToDTO(vehicleImage);
    }

    @Override
    public VehicleImageDto getVehicleImageById(Long id) {
        return vehicleImageRepository.findById(id)
                .map(VehicleImageMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("VehicleImage not found for id:" + id));
    }

    @Override
    public List<VehicleImageDto> getAllVehicleImages() {
        return vehicleImageRepository.findAll().stream()
                .map(VehicleImageMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleImageDto updateVehicleImage(Long id, VehicleImageDto vehicleImageDTO) {
        VehicleImage existingVehicleImage = vehicleImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehicleImage not found for id:" + id));

        Vehicle vehicle = vehicleRepository.findById(vehicleImageDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + vehicleImageDTO.getVehicleId()));

        existingVehicleImage.setVehicle(vehicle);
        existingVehicleImage.setImageUrl(vehicleImageDTO.getImageUrl());

        VehicleImage updatedVehicleImage = vehicleImageRepository.save(existingVehicleImage);
        return VehicleImageMapper.entityToDTO(updatedVehicleImage);
    }

    @Override
    public void deleteVehicleImage(Long id) {
        VehicleImage vehicleImage = vehicleImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehicleImage not found for id:" + id));
        vehicleImageRepository.delete(vehicleImage);
    }
}
