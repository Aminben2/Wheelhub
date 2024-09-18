package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.appointementsDtos.AppointmentDto;
import java.util.List;

public interface AppointmentService {
  AppointmentDto createAppointment(AppointmentDto appointmentDTO);

  AppointmentDto getAppointmentById(Long id);

  List<AppointmentDto> getAllAppointments();

  AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDTO);

  void deleteAppointment(Long id);
}
