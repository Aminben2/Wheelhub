package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.DealershipDTO;
import com.WheelHub.WheelHub.entity.Dealership;

public class DealershipMapper {

    public static DealershipDTO entityToDTO(Dealership dealership) {
        return DealershipDTO.builder()
                .id(dealership.getId())
                .name(dealership.getName())
                .location(dealership.getLocation())
                .contactInfo(dealership.getContactInfo())
                .createdAt(dealership.getCreatedAt())
                .updatedAt(dealership.getUpdatedAt())
                .build();
    }

    public static Dealership dtoToEntity(DealershipDTO dealershipDTO) {
        return Dealership.builder()
                .id(dealershipDTO.getId())
                .name(dealershipDTO.getName())
                .location(dealershipDTO.getLocation())
                .contactInfo(dealershipDTO.getContactInfo())
                .createdAt(dealershipDTO.getCreatedAt())
                .updatedAt(dealershipDTO.getUpdatedAt())
                .build();
    }
}
