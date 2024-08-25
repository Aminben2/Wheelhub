package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.NotificationDTO;
import com.WheelHub.WheelHub.entity.Notification;

import java.util.List;

public interface NotificationService {

    NotificationDTO createNotification(NotificationDTO notificationDTO);

    NotificationDTO getNotificationById(Long id);

    List<NotificationDTO> getAllNotifications();

    NotificationDTO updateNotification(Long id, NotificationDTO notificationDTO);

    void deleteNotification(Long id);
    Notification findById(Long id);
}
