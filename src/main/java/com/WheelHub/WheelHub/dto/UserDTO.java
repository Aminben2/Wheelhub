package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ArrayList<VehicleDTO> vehicles;
    private ArrayList<AppointmentDTO> appointments;
    private ArrayList<NotificationDTO> notifications;
    private ArrayList<SavedSearchDTO> savedSearches;
}
