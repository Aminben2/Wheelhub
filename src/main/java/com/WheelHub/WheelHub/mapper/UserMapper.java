package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.UserDTO;
import com.WheelHub.WheelHub.entity.User;

import java.util.Collections;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDTO entityToDTO(User user) {
        if (user == null) {
            return null; // or throw an exception depending on your use case
        }

        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .roles(user.getRoles() == null ? Collections.emptyList() : user.getRoles().stream()
                        .map(RoleMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .vehicles(user.getVehicles() == null ? Collections.emptyList() : user.getVehicles().stream()
                        .map(VehicleMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .appointments(user.getAppointments() == null ? Collections.emptyList() : user.getAppointments().stream()
                        .map(AppointmentMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .notifications(user.getNotifications() == null ? Collections.emptyList() : user.getNotifications().stream()
                        .map(NotificationMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .savedSearches(user.getSavedSearches() == null ? Collections.emptyList() : user.getSavedSearches().stream()
                        .map(SavedSearchMapper::entityToDTO)
                        .collect(Collectors.toList()))
                .build();
    }

    public static User dtoToEntity(UserDTO userDTO) {
        if (userDTO == null) {
            return null; // or throw an exception depending on your use case
        }

        return User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .roles(userDTO.getRoles() == null ? Collections.emptyList() : userDTO.getRoles().stream()
                        .map(RoleMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .vehicles(userDTO.getVehicles() == null ? Collections.emptyList() : userDTO.getVehicles().stream()
                        .map(VehicleMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .appointments(userDTO.getAppointments() == null ? Collections.emptyList() : userDTO.getAppointments().stream()
                        .map(AppointmentMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .notifications(userDTO.getNotifications() == null ? Collections.emptyList() : userDTO.getNotifications().stream()
                        .map(NotificationMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .savedSearches(userDTO.getSavedSearches() == null ? Collections.emptyList() : userDTO.getSavedSearches().stream()
                        .map(SavedSearchMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}


