package com.WheelHub.WheelHub.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private Set<Long> roleIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<Long> vehicleIds;
    private List<Long> appointmentIds;
    private List<Long> notificationIds;
    private List<Long> savedSearchIds;
}
