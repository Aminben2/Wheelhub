package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.userDtos.UserDto;
import com.WheelHub.WheelHub.entity.User;
import com.WheelHub.WheelHub.entity.Role;
import com.WheelHub.WheelHub.entity.Vehicle;
import com.WheelHub.WheelHub.entity.Appointment;
import com.WheelHub.WheelHub.entity.Notification;
import com.WheelHub.WheelHub.entity.SavedSearch;
import com.WheelHub.WheelHub.service.RoleService;
import com.WheelHub.WheelHub.service.VehicleService;
import com.WheelHub.WheelHub.service.AppointmentService;
import com.WheelHub.WheelHub.service.NotificationService;
import com.WheelHub.WheelHub.service.SavedSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final RoleService roleService;
    private final VehicleService vehicleService;
    private final AppointmentService appointmentService;
    private final NotificationService notificationService;
    private final SavedSearchService savedSearchService;

    public UserDto entityToDTO(User user) {
        Set<Long> roleIds = user.getRoles() != null ? user.getRoles().stream()
                .map(Role::getId)
                .collect(Collectors.toSet()) : Set.of();

        List<Long> vehicleIds = user.getVehicles() != null ? user.getVehicles().stream()
                .map(Vehicle::getId)
                .collect(Collectors.toList()) : List.of();

        List<Long> appointmentIds = user.getAppointments() != null ? user.getAppointments().stream()
                .map(Appointment::getId)
                .collect(Collectors.toList()) : List.of();

        List<Long> notificationIds = user.getNotifications() != null ? user.getNotifications().stream()
                .map(Notification::getId)
                .collect(Collectors.toList()) : List.of();

        List<Long> savedSearchIds = user.getSavedSearches() != null ? user.getSavedSearches().stream()
                .map(SavedSearch::getId)
                .collect(Collectors.toList()) : List.of();

        return UserDto.builder()
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roleIds(roleIds)
                .build();
    }

    public User dtoToEntity(UserDto userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .build();

        Set<Role> roles = userDTO.getRoleIds().stream()
                .map(roleService::findById)
                .collect(Collectors.toSet());

        user.setRoles(roles);
        return user;
    }
}
