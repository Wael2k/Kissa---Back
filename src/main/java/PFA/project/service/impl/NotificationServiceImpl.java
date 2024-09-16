package PFA.project.service.impl;

import PFA.project.dao.NotificationRepository;
import PFA.project.dao.entity.Notification;
import PFA.project.dto.notificationDto.NotificationDto;
import PFA.project.mapper.NotificationMapper;
import PFA.project.persistence.service.AbstractServiceImpl;
import PFA.project.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class NotificationServiceImpl extends AbstractServiceImpl<Notification, NotificationRepository> implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private NotificationMapper notificationMapper;
    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        super(notificationRepository);
    }


    @Override
    public Notification createOrUpdateNotification(Notification notification) {
       return super.createOrUpdate(notification);
    }

    @Override
    public List<NotificationDto> getAllValidNotificationsByReceiver(UUID uuidReceiver) {
            List<Notification> notifications = notificationRepository.findAllNotificationValidByReceiver(uuidReceiver);
            return notificationMapper.listNotificationsToListNotificationsDto(notifications);
    }

    @Override
    public void seenNotifications(UUID uuidReceiver) {
        List<Notification> notifications = notificationRepository.findAllNotificationValidByReceiver(uuidReceiver);
        notifications.forEach(notification -> {
            notification.setSeen(true);
            createOrUpdateNotification(notification);
        });
    }

}
