package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryDto;
import com.WheelHub.WheelHub.dto.vehicleCategoryDtos.VehicleCategoryResponseDto;
import com.WheelHub.WheelHub.entity.VehicleCategory;
import org.springframework.stereotype.Component;

@Component
public class VehicleCategoryMapper {

  public static VehicleCategoryDto entityToDTO(VehicleCategory vehicleCategory) {
    return VehicleCategoryDto.builder().categoryName(vehicleCategory.getCategoryName()).build();
  }

  public static VehicleCategoryResponseDto entityToResponseDTO(VehicleCategory vehicleCategory) {
    return VehicleCategoryResponseDto.builder()
        .id(vehicleCategory.getId())
        .categoryName(vehicleCategory.getCategoryName())
        .build();
  }

  public static VehicleCategory dtoToEntity(VehicleCategoryDto vehicleCategoryDto) {
    return VehicleCategory.builder().categoryName(vehicleCategoryDto.getCategoryName()).build();
  }
}
