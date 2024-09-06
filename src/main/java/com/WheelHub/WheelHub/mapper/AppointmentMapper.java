package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.appointementsDtos.AppointmentDto;
import com.WheelHub.WheelHub.entity.Appointment;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class AppointmentMapper {

    public static AppointmentDto entityToDTO(Appointment appointment) {
        return AppointmentDto.builder()
                .userId(appointment.getUser() != null ? appointment.getUser().getId() : null)
                .vehicleId(appointment.getVehicle() != null ? appointment.getVehicle().getId() : null)
                .appointmentType(appointment.getAppointmentType())
                .scheduledAt(appointment.getScheduledAt())
                .build();
    }

    public static Appointment dtoToEntity(AppointmentDto appointmentDTO) {
        Appointment appointment = Appointment.builder()
                .appointmentType(appointmentDTO.getAppointmentType())
                .scheduledAt(appointmentDTO.getScheduledAt())
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
