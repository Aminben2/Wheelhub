package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;
import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeResponseDto;
import com.WheelHub.WheelHub.entity.VehicleType;
import com.WheelHub.WheelHub.mapper.VehicleTypeMapper;
import com.WheelHub.WheelHub.repository.VehicleTypeRepository;
import com.WheelHub.WheelHub.service.VehicleTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    @Transactional
    public VehicleTypeResponseDto save(VehicleTypeDto vehicleTypeDto) {
        VehicleType vehicleType = VehicleTypeMapper.dtoToEntity(vehicleTypeDto);
        vehicleType = vehicleTypeRepository.save(vehicleType);
        return VehicleTypeMapper.entityToResponseDTO(vehicleType);
    }

    @Override
    @Transactional
    public VehicleTypeResponseDto update(Long id, VehicleTypeDto vehicleTypeDto) {
        VehicleType existingVehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found for id: " + id));

        existingVehicleType.setTypeName(vehicleTypeDto.getTypeName());

        VehicleType updatedVehicleType = vehicleTypeRepository.save(existingVehicleType);
        return VehicleTypeMapper.entityToResponseDTO(updatedVehicleType);
    }


    @Override
    public VehicleTypeResponseDto findById(Long id) {
        return vehicleTypeRepository.findById(id)
                .map(VehicleTypeMapper::entityToResponseDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found for id: " + id));
    }

    @Override
    public List<VehicleTypeResponseDto> findAll() {
        return vehicleTypeRepository.findAll().stream()
                .map(VehicleTypeMapper::entityToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found for id: " + id));
        vehicleTypeRepository.delete(vehicleType);
    }
}
