package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.UserDTO;
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

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserMapper {

    private final RoleService roleService;
    private final VehicleService vehicleService;
    private final AppointmentService appointmentService;
    private final NotificationService notificationService;
    private final SavedSearchService savedSearchService;

    public UserMapper(RoleService roleService, VehicleService vehicleService, AppointmentService appointmentService,
                      NotificationService notificationService, SavedSearchService savedSearchService) {
        this.roleService = roleService;
        this.vehicleService = vehicleService;
        this.appointmentService = appointmentService;
        this.notificationService = notificationService;
        this.savedSearchService = savedSearchService;
    }

    public UserDTO entityToDTO(User user) {
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

        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .roleIds(roleIds)
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .vehicleIds(vehicleIds)
                .appointmentIds(appointmentIds)
                .notificationIds(notificationIds)
                .savedSearchIds(savedSearchIds)
                .build();
    }

    public User dtoToEntity(UserDTO userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .name(userDTO.getName())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .createdAt(userDTO.getCreatedAt())
                .updatedAt(userDTO.getUpdatedAt())
                .build();

        // Fetch entities for roles, vehicles, appointments, notifications, and saved searches
        Set<Role> roles = userDTO.getRoleIds().stream()
                .map(roleId -> roleService.findById(roleId))
                .collect(Collectors.toSet());

        List<Vehicle> vehicles = userDTO.getVehicleIds().stream()
                .map(vehicleId -> vehicleService.findById(vehicleId))
                .collect(Collectors.toList());

        List<Appointment> appointments = userDTO.getAppointmentIds().stream()
                .map(appointmentId -> appointmentService.findById(appointmentId))
                .collect(Collectors.toList());

        List<Notification> notifications = userDTO.getNotificationIds().stream()
                .map(notificationId -> notificationService.findById(notificationId))
                .collect(Collectors.toList());

        List<SavedSearch> savedSearches = userDTO.getSavedSearchIds().stream()
                .map(savedSearchId -> savedSearchService.findById(savedSearchId))
                .collect(Collectors.toList());

        user.setRoles(roles);
        user.setVehicles(vehicles);
        user.setAppointments(appointments);
        user.setNotifications(notifications);
        user.setSavedSearches(savedSearches);

        return user;
    }
}
