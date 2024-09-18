package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;
import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryResponseDto;
import com.WheelHub.WheelHub.entity.VehicleCategory;
import com.WheelHub.WheelHub.mapper.VehicleCategoryMapper;
import com.WheelHub.WheelHub.repository.VehicleCategoryRepository;
import com.WheelHub.WheelHub.service.VehicleCategoryService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VehicleCategoryServiceImpl implements VehicleCategoryService {

  private final VehicleCategoryRepository vehicleCategoryRepository;

  @Override
  @Transactional
  public VehicleCategoryResponseDto save(VehicleCategoryDto vehicleCategoryDto) {
    VehicleCategory vehicleCategory = VehicleCategoryMapper.dtoToEntity(vehicleCategoryDto);
    vehicleCategory = vehicleCategoryRepository.save(vehicleCategory);
    return VehicleCategoryMapper.entityToResponseDTO(vehicleCategory);
  }

  @Override
  @Transactional
  public VehicleCategoryResponseDto update(Long id, VehicleCategoryDto vehicleCategoryDto) {
    VehicleCategory existingVehicleCategory =
        vehicleCategoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Vehicle category not found for id: " + id));

    existingVehicleCategory.setCategoryName(vehicleCategoryDto.getCategoryName());

    VehicleCategory updatedVehicleCategory =
        vehicleCategoryRepository.save(existingVehicleCategory);
    return VehicleCategoryMapper.entityToResponseDTO(updatedVehicleCategory);
  }

  @Override
  public VehicleCategoryResponseDto findById(Long id) {
    return vehicleCategoryRepository
        .findById(id)
        .map(VehicleCategoryMapper::entityToResponseDTO)
        .orElseThrow(() -> new EntityNotFoundException("Vehicle category not found for id: " + id));
  }

  @Override
  public List<VehicleCategoryResponseDto> findAll() {
    return vehicleCategoryRepository.findAll().stream()
        .map(VehicleCategoryMapper::entityToResponseDTO)
        .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    VehicleCategory vehicleCategory =
        vehicleCategoryRepository
            .findById(id)
            .orElseThrow(
                () -> new EntityNotFoundException("Vehicle category not found for id: " + id));
    vehicleCategoryRepository.delete(vehicleCategory);
  }
}
