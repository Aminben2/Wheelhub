package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.InquiryDTO;
import com.WheelHub.WheelHub.entity.Inquiry;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;

public class InquiryMapper {

    public static InquiryDTO entityToDTO(Inquiry inquiry) {
        return InquiryDTO.builder()
                .id(inquiry.getId())
                .userId(inquiry.getUser() != null ? inquiry.getUser().getId() : null)
                .vehicleId(inquiry.getVehicle() != null ? inquiry.getVehicle().getId() : null)
                .message(inquiry.getMessage())
                .createdAt(inquiry.getCreatedAt())
                .build();
    }

    public static Inquiry dtoToEntity(InquiryDTO inquiryDTO) {
        Inquiry inquiry = Inquiry.builder()
                .id(inquiryDTO.getId())
                .message(inquiryDTO.getMessage())
                .createdAt(inquiryDTO.getCreatedAt())
                .build();

        if (inquiryDTO.getUserId() != null) {
            User user = new User();
            user.setId(inquiryDTO.getUserId());
            inquiry.setUser(user);
        }
        if (inquiryDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(inquiryDTO.getVehicleId());
            inquiry.setVehicle(vehicle);
        }

        return inquiry;
    }
}
