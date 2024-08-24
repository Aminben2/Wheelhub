package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.AppointmentDTO;
import com.WheelHub.WheelHub.entity.Appointment;

import java.util.List;

public interface AppointmentService {
    AppointmentDTO createAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO getAppointmentById(Long id);
    List<AppointmentDTO> getAllAppointments();
    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointmentDTO);
    void deleteAppointment(Long id);
    Appointment findById(Long id);

}
