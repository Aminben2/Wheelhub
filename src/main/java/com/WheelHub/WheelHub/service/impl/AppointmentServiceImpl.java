package com.WheelHub.WheelHub.service.impl;

import com.WheelHub.WheelHub.dto.appointementsDtos.AppointmentDto;
import com.WheelHub.WheelHub.entity.Appointment;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.mapper.AppointmentMapper;
import com.WheelHub.WheelHub.repository.AppointmentRepository;
import com.WheelHub.WheelHub.repository.UserRepository;
import com.WheelHub.WheelHub.repository.VehicleRepository;
import com.WheelHub.WheelHub.service.AppointmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDTO) {
        User user = userRepository.findById(appointmentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + appointmentDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(appointmentDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + appointmentDTO.getVehicleId()));

        Appointment appointment = AppointmentMapper.dtoToEntity(appointmentDTO);
        appointment.setUser(user);
        appointment.setVehicle(vehicle);

        appointment = appointmentRepository.save(appointment);
        return AppointmentMapper.entityToDTO(appointment);
    }

    @Override
    public Appointment findById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found for id:" + id));
    }
    @Override
    public AppointmentDto getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(AppointmentMapper::entityToDTO)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found for id:" + id));
    }

    @Override
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll().stream()
                .map(AppointmentMapper::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentDto updateAppointment(Long id, AppointmentDto appointmentDTO) {
        Appointment existingAppointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found for id:" + id));

        User user = userRepository.findById(appointmentDTO.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found for id:" + appointmentDTO.getUserId()));
        Vehicle vehicle = vehicleRepository.findById(appointmentDTO.getVehicleId())
                .orElseThrow(() -> new EntityNotFoundException("Vehicle not found for id:" + appointmentDTO.getVehicleId()));

        existingAppointment.setUser(user);
        existingAppointment.setVehicle(vehicle);
        existingAppointment.setAppointmentType(appointmentDTO.getAppointmentType());
        existingAppointment.setScheduledAt(appointmentDTO.getScheduledAt());

        Appointment updatedAppointment = appointmentRepository.save(existingAppointment);
        return AppointmentMapper.entityToDTO(updatedAppointment);
    }

    @Override
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found for id:" + id));
        appointmentRepository.delete(appointment);
    }
}
