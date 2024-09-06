package com.WheelHub.WheelHub.dto.appointementsDtos;

import com.WheelHub.WheelHub.constant.enums.AppointmentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentDto {

    @NotNull(message = "User ID is mandatory") // Ensure user ID is provided
    private Long userId;

    @NotNull(message = "Vehicle ID is mandatory") // Ensure vehicle ID is provided
    private Long vehicleId;

    @NotNull(message = "Appointment type is mandatory") // Ensure appointment type is provided
    private AppointmentType appointmentType;

    @NotNull(message = "Scheduled date and time is mandatory") // Ensure scheduledAt is provided
    @FutureOrPresent(message = "Scheduled date and time must be in the future or present") // Validate that the date and time are not in the past
    private LocalDateTime scheduledAt;

}
