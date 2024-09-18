package com.WheelHub.WheelHub.dto.appointementsDtos;

import com.WheelHub.WheelHub.constant.enums.AppointmentType;
import java.time.LocalDateTime;

public record AppointmentResponseDto(
    AppointmentType appointmentType, LocalDateTime scheduledAt, LocalDateTime createdAt) {}
