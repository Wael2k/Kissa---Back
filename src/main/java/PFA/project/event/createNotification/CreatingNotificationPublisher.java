package PFA.project.event.createNotification;


import PFA.project.enums.NotificationContextEnum;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class CreatingNotificationPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

    public void publish(UUID uuidReceiver, String message, NotificationContextEnum notificationContext) {
        this.publisher.publishEvent(new CreatingNotification(this, uuidReceiver, message,notificationContext));
    }
}