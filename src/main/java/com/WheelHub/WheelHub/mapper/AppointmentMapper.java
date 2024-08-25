package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.AppointmentDTO;
import com.WheelHub.WheelHub.entity.Appointment;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class AppointmentMapper {

    public static AppointmentDTO entityToDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .userId(appointment.getUser() != null ? appointment.getUser().getId() : null)
                .vehicleId(appointment.getVehicle() != null ? appointment.getVehicle().getId() : null)
                .appointmentType(appointment.getAppointmentType())
                .scheduledAt(appointment.getScheduledAt())
                .createdAt(appointment.getCreatedAt())
                .build();
    }

    public static Appointment dtoToEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = Appointment.builder()
                .id(appointmentDTO.getId())
                .appointmentType(appointmentDTO.getAppointmentType())
                .scheduledAt(appointmentDTO.getScheduledAt())
                .createdAt(appointmentDTO.getCreatedAt())
                .build();

        if (appointmentDTO.getUserId() != null) {
            User user = new User();
            user.setId(appointmentDTO.getUserId());
            appointment.setUser(user);
        }
        if (appointmentDTO.getVehicleId() != null) {
            Vehicle vehicle = new Vehicle();
            vehicle.setId(appointmentDTO.getVehicleId());
            appointment.setVehicle(vehicle);
        }

        return appointment;
    }
}
