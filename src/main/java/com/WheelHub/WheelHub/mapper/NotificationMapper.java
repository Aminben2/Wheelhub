package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.NotificationDTO;
import com.WheelHub.WheelHub.entity.Notification;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;


@Component
public class NotificationMapper {

    public static NotificationDTO entityToDTO(Notification notification) {
        return NotificationDTO.builder()
                .id(notification.getId())
                .userId(notification.getUser() != null ? notification.getUser().getId() : null)
                .notificationType(notification.getNotificationType())
                .details(notification.getDetails())
                .createdAt(notification.getCreatedAt())
                .build();
    }

    public static Notification dtoToEntity(NotificationDTO notificationDTO) {
        Notification notification = Notification.builder()
                .id(notificationDTO.getId())
                .notificationType(notificationDTO.getNotificationType())
                .details(notificationDTO.getDetails())
                .createdAt(notificationDTO.getCreatedAt())
                .build();

        if (notificationDTO.getUserId() != null) {
            User user = new User();
            user.setId(notificationDTO.getUserId());
            notification.setUser(user);
        }

        return notification;
    }
}
