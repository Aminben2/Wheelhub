package com.WheelHub.WheelHub.dto;

import com.WheelHub.WheelHub.constant.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class NotificationDTO {

    private Long id;
    private Long userId;
    private NotificationType notificationType;
    private String details;
    private LocalDateTime createdAt;
}
