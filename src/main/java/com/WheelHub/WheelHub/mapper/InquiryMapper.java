package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.InquiryDtos.InquiryDto;
import com.WheelHub.WheelHub.entity.Inquiry;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class InquiryMapper {

  public static InquiryDto entityToDTO(Inquiry inquiry) {
    return InquiryDto.builder()
        .userId(inquiry.getUser() != null ? inquiry.getUser().getId() : null)
        .vehicleId(inquiry.getVehicle() != null ? inquiry.getVehicle().getId() : null)
        .message(inquiry.getMessage())
        .build();
  }

  public static Inquiry dtoToEntity(InquiryDto inquiryDTO) {
    Inquiry inquiry = Inquiry.builder().message(inquiryDTO.getMessage()).build();

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
