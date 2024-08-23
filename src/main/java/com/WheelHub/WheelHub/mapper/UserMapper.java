package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.UserDTO;
import com.WheelHub.WheelHub.entity.User;

import java.util.stream.Collectors;

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
                        .collect(Collectors.toList())) // Collect to List
                .vehicles(user.getVehicles().stream()
                        .map(VehicleMapper::entityToDTO)
                        .collect(Collectors.toList())) // Collect to List
                .appointments(user.getAppointments().stream()
                        .map(AppointmentMapper::entityToDTO)
                        .collect(Collectors.toList())) // Collect to List
                .notifications(user.getNotifications().stream()
                        .map(NotificationMapper::entityToDTO)
                        .collect(Collectors.toList())) // Collect to List
                .savedSearches(user.getSavedSearches().stream()
                        .map(SavedSearchMapper::entityToDTO)
                        .collect(Collectors.toList())) // Collect to List
                .build();
    }

    public static User dtoToEntity(UserDTO userDTO) {
        return  User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .roles(userDTO.getRoles().stream()
                        .map(RoleMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .vehicles(userDTO.getVehicles().stream()
                        .map(VehicleMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .appointments(userDTO.getAppointments().stream()
                        .map(AppointmentMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .notifications(userDTO.getNotifications().stream()
                        .map(NotificationMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .savedSearches(userDTO.getSavedSearches().stream()
                        .map(SavedSearchMapper::dtoToEntity)
                        .collect(Collectors.toList()))
                .build();
    }
}

