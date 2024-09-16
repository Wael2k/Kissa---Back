package PFA.project.event.createNotification;
import PFA.project.dao.entity.Notification;
import PFA.project.dao.entity.SocketIoSession;
import PFA.project.service.NotificationService;
import PFA.project.service.SocketIoSessionService;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
@Component
@Slf4j
public class CreatingNotificationHandler implements ApplicationListener<CreatingNotification> {
    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SocketIOServer socketIOServer;
    @Autowired
    private SocketIoSessionService socketIoSessionService;

    @Override
    public void onApplicationEvent(CreatingNotification event) {
        Notification notification = new Notification();
        notification.setMessage(event.getMessage());
        notification.setReceiverUuid(event.getUuidReceiver());
        notification.setNotificationContext(event.getNotificationContext());
        notification = notificationService.createOrUpdateNotification(notification);
        log.info("send notification ");
        SocketIoSession socketIoSession = socketIoSessionService.getByClientUuid(notification.getReceiverUuid()).orElse(null);
        if(socketIoSession != null)
            if(this.socketIOServer.getClient(socketIoSession.getSession()) != null)
                this.socketIOServer.getClient(socketIoSession.getSession()).sendEvent(notification.getNotificationContext().name(), notification.getMessage());


    }
}
