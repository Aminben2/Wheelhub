package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;
import com.WheelHub.WheelHub.entity.VehicleCategory;
import com.WheelHub.WheelHub.mapper.VehicleCategoryMapper;
import com.WheelHub.WheelHub.repository.VehicleCategoryRepository;
import com.WheelHub.WheelHub.service.VehicleCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

    private final VehicleCategoryRepository vehicleCategoryRepository;

    @Override
    @Transactional
    public VehicleCategoryDto save(VehicleCategoryDto vehicleCategoryDto) {
        VehicleCategory vehicleCategory = VehicleCategoryMapper.dtoToEntity(vehicleCategoryDto);
        vehicleCategory = vehicleCategoryRepository.save(vehicleCategory);
        return VehicleCategoryMapper.entityToDTO(vehicleCategory);
    }

    @Override
    @Transactional
    public VehicleCategoryDto update(Long id, VehicleCategoryDto vehicleCategoryDto) {
        VehicleCategory existingVehicleCategory = vehicleCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle category not found for id: " + id));

        existingVehicleCategory.setCategoryName(vehicleCategoryDto.getCategoryName());

        VehicleCategory updatedVehicleCategory = vehicleCategoryRepository.save(existingVehicleCategory);
        return VehicleCategoryMapper.entityToDTO(updatedVehicleCategory);
    }


    @Override
    public VehicleCategoryDto findById(Long id) {
        return vehicleCategoryRepository.findById(id)
                .map(VehicleCategoryMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle category not found for id: " + id));
    }

    @Override
    public List<VehicleCategoryDto> findAll() {
        return vehicleCategoryRepository.findAll().stream()
                .map(VehicleCategoryMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        VehicleCategory vehicleCategory = vehicleCategoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vehicle category not found for id: " + id));
        vehicleCategoryRepository.delete(vehicleCategory);
    }
}
