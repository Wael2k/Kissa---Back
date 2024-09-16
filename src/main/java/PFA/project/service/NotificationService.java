package PFA.project.service;

import PFA.project.dao.entity.Notification;
import PFA.project.dto.notificationDto.NotificationDto;

import java.util.List;
import java.util.UUID;


public interface NotificationService {
   Notification createOrUpdateNotification(Notification notification);
   List<NotificationDto> getAllValidNotificationsByReceiver(UUID uuidReceiver);

   void seenNotifications(UUID uuidReceiver);
}
