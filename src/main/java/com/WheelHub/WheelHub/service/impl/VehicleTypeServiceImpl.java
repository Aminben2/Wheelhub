package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleTypeDtos.VehicleTypeDto;
import com.WheelHub.WheelHub.entity.VehicleType;
import com.WheelHub.WheelHub.mapper.VehicleTypeMapper;
import com.WheelHub.WheelHub.repository.VehicleTypeRepository;
import com.WheelHub.WheelHub.service.VehicleTypeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleTypeServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Override
    public VehicleTypeDto save(VehicleTypeDto vehicleTypeDto) {
        VehicleType vehicleType = VehicleTypeMapper.dtoToEntity(vehicleTypeDto);
        vehicleType = vehicleTypeRepository.save(vehicleType);
        return VehicleTypeMapper.entityToDTO(vehicleType);
    }

    @Override
    public VehicleTypeDto update(Long id, VehicleTypeDto vehicleTypeDto) {
        VehicleType existingVehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found for id: " + id));

        existingVehicleType.setTypeName(vehicleTypeDto.getTypeName());

        VehicleType updatedVehicleType = vehicleTypeRepository.save(existingVehicleType);
        return VehicleTypeMapper.entityToDTO(updatedVehicleType);
    }


    @Override
    public VehicleTypeDto findById(Long id) {
        return vehicleTypeRepository.findById(id)
                .map(VehicleTypeMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found for id: " + id));
    }

    @Override
    public List<VehicleTypeDto> findAll() {
        return vehicleTypeRepository.findAll().stream()
                .map(VehicleTypeMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        VehicleType vehicleType = vehicleTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle type not found for id: " + id));
        vehicleTypeRepository.delete(vehicleType);
    }
}
