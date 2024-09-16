package PFA.project.event.createNotification;

import PFA.project.enums.NotificationContextEnum;
import PFA.project.enums.TypeRegisterEnum;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;

@Getter
public class CreatingNotification  extends ApplicationEvent {
    private UUID uuidReceiver;
    private String message;
    private NotificationContextEnum notificationContext;
    public CreatingNotification(Object source, UUID uuidReceiver , String message, NotificationContextEnum notificationContext) {
        super(source);
        this.uuidReceiver = uuidReceiver;
        this.message = message;
        this.notificationContext = notificationContext;

    }
}
