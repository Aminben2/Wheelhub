package com.WheelHub.WheelHub.dto.notificationDtos;

import com.WheelHub.WheelHub.constant.enums.NotificationType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotificationDto {

    @NotNull(message = "User ID is mandatory")
    private Long userId;

    @NotNull(message = "Notification type is mandatory")
    private NotificationType notificationType;

    @Size(max = 500, message = "Details must not exceed 500 characters")
    private String details;
}
