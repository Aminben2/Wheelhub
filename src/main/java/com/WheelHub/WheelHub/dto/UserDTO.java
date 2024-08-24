package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<VehicleDTO> vehicles;
    private List<AppointmentDTO> appointments;
    private List<NotificationDTO> notifications;
    private List<SavedSearchDTO> savedSearches;
    private List<RoleDTO> roles;
}
