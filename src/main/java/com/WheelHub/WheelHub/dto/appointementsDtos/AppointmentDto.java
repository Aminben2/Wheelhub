package com.WheelHub.WheelHub.dto.appointementsDtos;

import com.WheelHub.WheelHub.constant.enums.AppointmentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AppointmentDto {

  @NotNull(message = "User ID is mandatory")
  private Long userId;

  @NotNull(message = "Vehicle ID is mandatory")
  private Long vehicleId;

  @NotNull(message = "Appointment type is mandatory")
  private AppointmentType appointmentType;

  @NotNull(message = "Scheduled date and time is mandatory")
  @FutureOrPresent(message = "Scheduled date and time must be in the future or present")
  // time are not in the past
  private LocalDateTime scheduledAt;
}
