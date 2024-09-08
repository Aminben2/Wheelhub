package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleFeatureDtos.VehicleFeatureDto;
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
    public VehicleFeatureDto save(VehicleFeatureDto vehicleFeatureDto) {
        VehicleFeature vehicleFeature = VehicleFeatureMapper.dtoToEntity(vehicleFeatureDto);
        vehicleFeature = vehicleFeatureRepository.save(vehicleFeature);
        return VehicleFeatureMapper.entityToDTO(vehicleFeature);
    }

    @Override
    public VehicleFeatureDto update(Long id, VehicleFeatureDto vehicleFeatureDto) {
        VehicleFeature existingVehicleFeature = vehicleFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle feature not found for id: " + id));

        existingVehicleFeature.setFeatureName(vehicleFeatureDto.getFeatureName());

        VehicleFeature updatedVehicleFeature = vehicleFeatureRepository.save(existingVehicleFeature);
        return VehicleFeatureMapper.entityToDTO(updatedVehicleFeature);
    }


    @Override
    public VehicleFeatureDto findById(Long id) {
        return vehicleFeatureRepository.findById(id)
                .map(VehicleFeatureMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle feature not found for id: " + id));
    }

    @Override
    public List<VehicleFeatureDto> findAll() {
        return vehicleFeatureRepository.findAll().stream()
                .map(VehicleFeatureMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        VehicleFeature vehicleFeature = vehicleFeatureRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle feature not found for id: " + id));
        vehicleFeatureRepository.delete(vehicleFeature);
    }
}
