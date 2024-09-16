package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageDto;
import com.WheelHub.WheelHub.dto.vehicleImagesDtos.VehicleImageResponseDto;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.entity.VehicleImage;
import com.WheelHub.WheelHub.mapper.VehicleImageMapper;
import com.WheelHub.WheelHub.repository.VehicleImageRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.VehicleImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleImageServiceImpl implements VehicleImageService {

    private final VehicleImageRepository vehicleImageRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    @Transactional
    public VehicleImageResponseDto createVehicleImage(VehicleImageDto vehicleImageDTO) {
        Vehicle vehicle = vehicleRepository.findById(vehicleImageDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + vehicleImageDTO.getVehicleId()));

        VehicleImage vehicleImage = VehicleImageMapper.dtoToEntity(vehicleImageDTO);
        vehicleImage.setVehicle(vehicle);

        vehicleImage = vehicleImageRepository.save(vehicleImage);
        return VehicleImageMapper.entityToResponseDTO(vehicleImage);
    }

    @Override
    public VehicleImageResponseDto getVehicleImageById(Long id) {
        return vehicleImageRepository.findById(id)
                .map(VehicleImageMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("VehicleImage not found for id:" + id));
    }

    @Override
    public List<VehicleImageResponseDto> getImagesForVehicle(Long id) {
        return vehicleImageRepository.findByVehicleId(id).stream()
                .map(VehicleImageMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleImageResponseDto> getAllVehicleImages() {
        return vehicleImageRepository.findAll().stream()
                .map(VehicleImageMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleImageResponseDto updateVehicleImage(Long id, VehicleImageDto vehicleImageDTO) {
        VehicleImage existingVehicleImage = vehicleImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehicleImage not found for id:" + id));

        Vehicle vehicle = vehicleRepository.findById(vehicleImageDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + vehicleImageDTO.getVehicleId()));

        existingVehicleImage.setVehicle(vehicle);
        existingVehicleImage.setImageUrl(vehicleImageDTO.getImageUrl());

        VehicleImage updatedVehicleImage = vehicleImageRepository.save(existingVehicleImage);
        return VehicleImageMapper.entityToResponseDTO(updatedVehicleImage);
    }

    @Override
    public void deleteVehicleImage(Long id) {
        VehicleImage vehicleImage = vehicleImageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("VehicleImage not found for id:" + id));
        vehicleImageRepository.delete(vehicleImage);
    }
}
