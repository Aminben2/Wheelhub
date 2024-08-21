package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.UserDTO;
import com.WheelHub.WheelHub.entity.User;

public class UserMapper {

    public static UserDTO entityToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(user.getRoles().stream()
                        .map(RoleMapper::entityToDTO)
                        .collect(Collectors.toSet()))
                .vehicles(user.getVehicles().stream()
                        .map(VehicleMapper::entityToDTO)
                        .collect(Collectors.toSet()))
                .appointments(user.getAppointments().stream()
                        .map(AppointmentMapper::entityToDTO)
                        .collect(Collectors.toSet()))
                .notifications(user.getNotifications().stream()
                        .map(NotificationMapper::entityToDTO)
                        .collect(Collectors.toSet()))
                .savedSearches(user.getSavedSearches().stream()
                        .map(SavedSearchMapper::entityToDTO)
                        .collect(Collectors.toSet()))
                .build();
    }

    public static User dtoToEntity(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .roles(userDTO.getRoles().stream()
                        .map(RoleMapper::dtoToEntity)
                        .collect(Collectors.toSet()))
                .vehicles(userDTO.getVehicles().stream()
                        .map(VehicleMapper::dtoToEntity)
                        .collect(Collectors.toSet()))
                .appointments(userDTO.getAppointments().stream()
                        .map(AppointmentMapper::dtoToEntity)
                        .collect(Collectors.toSet()))
                .notifications(userDTO.getNotifications().stream()
                        .map(NotificationMapper::dtoToEntity)
                        .collect(Collectors.toSet()))
                .savedSearches(userDTO.getSavedSearches().stream()
                        .map(SavedSearchMapper::dtoToEntity)
                        .collect(Collectors.toSet()))
                .build();
    }

}
