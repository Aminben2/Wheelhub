package com.WheelHub.WheelHub.service;

import com.WheelHub.WheelHub.dto.notificationDtos.NotificationDto;
import com.WheelHub.WheelHub.entity.Notification;
import java.util.List;

public interface NotificationService {

  NotificationDto createNotification(NotificationDto notificationDTO);

  NotificationDto getNotificationById(Long id);

  List<NotificationDto> getAllNotifications();

  NotificationDto updateNotification(Long id, NotificationDto notificationDTO);

  void deleteNotification(Long id);

  Notification findById(Long id);
}
