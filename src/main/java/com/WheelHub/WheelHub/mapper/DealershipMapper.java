package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.dealershipDtos.DealershipDto;
import com.WheelHub.WheelHub.entity.Dealership;
import org.springframework.stereotype.Component;

@Component
public class DealershipMapper {

  public static DealershipDto entityToDTO(Dealership dealership) {
    return DealershipDto.builder()
        .name(dealership.getName())
        .location(dealership.getLocation())
        .contactInfo(dealership.getContactInfo())
        .build();
  }

  public static Dealership dtoToEntity(DealershipDto dealershipDTO) {
    return Dealership.builder()
        .name(dealershipDTO.getName())
        .location(dealershipDTO.getLocation())
        .contactInfo(dealershipDTO.getContactInfo())
        .build();
  }
}
