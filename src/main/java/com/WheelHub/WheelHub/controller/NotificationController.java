package com.WheelHub.WheelHub.controller;

import com.WheelHub.WheelHub.dto.notificationDtos.NotificationDto;
import com.WheelHub.WheelHub.service.impl.NotificationServiceImpl;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Validated
public class NotificationController {

    private final NotificationServiceImpl notificationService;

    @PostMapping("/")
    public ResponseEntity<NotificationDto> createNotification(@Valid @RequestBody NotificationDto notificationDTO) {
     
            NotificationDto createdNotification = notificationService.createNotification(notificationDTO);
            return new ResponseEntity<>(createdNotification, HttpStatus.CREATED);

    }

    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(@PathVariable @Min(1) Long id) {
     
            NotificationDto notificationDTO = notificationService.getNotificationById(id);
            return new ResponseEntity<>(notificationDTO, HttpStatus.OK);

    }

    @GetMapping("/all")
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {
            List<NotificationDto> notifications = notificationService.getAllNotifications();
            return new ResponseEntity<>(notifications, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(@PathVariable @Min(1) Long id,@Valid @RequestBody NotificationDto notificationDTO) {
     
            NotificationDto updatedNotification = notificationService.updateNotification(id, notificationDTO);
            return new ResponseEntity<>(updatedNotification, HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable @Min(1) Long id) {
     
            notificationService.deleteNotification(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}
