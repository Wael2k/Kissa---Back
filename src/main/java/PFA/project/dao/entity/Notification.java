package PFA.project.dao.entity;

import PFA.project.enums.NotificationContextEnum;
import PFA.project.persistence.dao.entity.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TABLE_NOTIFICATION")
@Data
public class Notification extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String message;
    private UUID receiverUuid;
    private boolean seen;
    @Enumerated(EnumType.STRING)
    private NotificationContextEnum notificationContext;




    @PrePersist
    private void generateUUID() {
        this.uuid = UUID.randomUUID();
    }
}
