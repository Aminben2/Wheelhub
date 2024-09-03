package com.WheelHub.WheelHub.dto.appointementsDtos;

import com.WheelHub.WheelHub.constant.enums.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentDTO {

    private Long id;
    private Long userId;
    private Long vehicleId;
    private AppointmentType appointmentType;
    private LocalDateTime scheduledAt;
    private LocalDateTime createdAt;
}
