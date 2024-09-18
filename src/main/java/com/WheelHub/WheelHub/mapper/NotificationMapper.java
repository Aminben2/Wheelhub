package com.WheelHub.WheelHub.mapper;

import com.WheelHub.WheelHub.dto.notificationDtos.NotificationDto;
import com.WheelHub.WheelHub.entity.Notification;
import com.WheelHub.WheelHub.entity.User;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

  public static NotificationDto entityToDTO(Notification notification) {
    return NotificationDto.builder()
        .userId(notification.getUser() != null ? notification.getUser().getId() : null)
        .notificationType(notification.getNotificationType())
        .details(notification.getDetails())
        .build();
  }

  public static Notification dtoToEntity(NotificationDto notificationDTO) {
    Notification notification =
        Notification.builder()
            .notificationType(notificationDTO.getNotificationType())
            .details(notificationDTO.getDetails())
            .build();

    if (notificationDTO.getUserId() != null) {
      User user = new User();
      user.setId(notificationDTO.getUserId());
      notification.setUser(user);
    }

    return notification;
  }
}
