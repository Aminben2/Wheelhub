package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {

    public UserDto entityToDTO(User user) {
        List<Long> vehicleIds = user.getVehicles() != null ? user.getVehicles().stream()
                .map(Vehicle::getId)
                .toList() : List.of();

        List<Long> appointmentIds = user.getAppointments() != null ? user.getAppointments().stream()
                .map(Appointment::getId)
                .toList() : List.of();

        List<Long> notificationIds = user.getNotifications() != null ? user.getNotifications().stream()
                .map(Notification::getId)
                .toList() : List.of();

        List<Long> savedSearchIds = user.getSavedSearches() != null ? user.getSavedSearches().stream()
                .map(SavedSearch::getId)
                .toList() : List.of();

        return UserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    public User dtoToEntity(UserDto userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .role(userDTO.getRole())
                .build();
        return user;
    }
}
