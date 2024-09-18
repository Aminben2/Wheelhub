package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.appointementsDtos.AppointmentDto;
import com.WheelHub.WheelHub.service.impl.AppointmentServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Validated
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;

    @PostMapping("/")
    public ResponseEntity<AppointmentDto> createAppointment(@Valid  @RequestBody AppointmentDto appointmentDTO) {
        AppointmentDto createdAppointment = appointmentService.createAppointment(appointmentDTO);
        return new ResponseEntity<>(createdAppointment, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentDto> getAppointmentById(@PathVariable @Min(1) Long id) {
        AppointmentDto appointmentDTO = appointmentService.getAppointmentById(id);
        return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppointmentDto>> getAllAppointments() {
        List<AppointmentDto> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable @Min(1) Long id,@Valid @RequestBody AppointmentDto appointmentDTO) {
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, appointmentDTO);
        return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable @Min(1) Long id) {
        appointmentService.deleteAppointment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
