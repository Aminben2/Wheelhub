package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureResponseDto;
import com.WheelHub.WheelHub.entity.VehicleFeature;
import com.WheelHub.WheelHub.mapper.VehicleFeatureMapper;
import com.WheelHub.WheelHub.repository.VehicleFeatureRepository;
import com.WheelHub.WheelHub.service.VehicleFeatureService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleFeatureServiceImpl implements VehicleFeatureService {

    private final VehicleFeatureRepository vehicleFeatureRepository;

    @Override
    public VehicleFeatureResponseDto save(VehicleFeatureDto vehicleFeatureDto) {
        VehicleFeature vehicleFeature = VehicleFeatureMapper.dtoToEntity(vehicleFeatureDto);
        vehicleFeature = vehicleFeatureRepository.save(vehicleFeature);
        return VehicleFeatureMapper.entityToResponseDTO(vehicleFeature);
    }

    @Override
    public VehicleFeatureResponseDto update(Long id, VehicleFeatureDto vehicleFeatureDto) {
        VehicleFeature existingVehicleFeature = vehicleFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle feature not found for id: " + id));

        existingVehicleFeature.setFeatureName(vehicleFeatureDto.getFeatureName());

        VehicleFeature updatedVehicleFeature = vehicleFeatureRepository.save(existingVehicleFeature);
        return VehicleFeatureMapper.entityToResponseDTO(updatedVehicleFeature);
    }


    @Override
    public VehicleFeatureResponseDto findById(Long id) {
        return vehicleFeatureRepository.findById(id)
                .map(VehicleFeatureMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle feature not found for id: " + id));
    }

    @Override
    public List<VehicleFeatureResponseDto> findAll() {
        return vehicleFeatureRepository.findAll().stream()
                .map(VehicleFeatureMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleFeatureResponseDto> findFeaturesForVehicle(Long id) {
        return vehicleFeatureRepository.findFeaturesByVehicleId(id).stream()
                .map(VehicleFeatureMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        VehicleFeature vehicleFeature = vehicleFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle feature not found for id: " + id));
        vehicleFeatureRepository.delete(vehicleFeature);
    }
}
