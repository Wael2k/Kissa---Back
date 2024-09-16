package PFA.project.dto.notificationDto;

import PFA.project.enums.NotificationContextEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class NotificationDto {
    private Long id;
    private UUID uuid;
    private UUID receiverUuid;
    private String message;
    private NotificationContextEnum notificationContext;
    private boolean seen;
}
