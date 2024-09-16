package PFA.project.dao;

import PFA.project.dao.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("SELECT n FROM Notification n WHERE n.seen = false AND n.receiverUuid = :uuidReceiver")
    List<Notification> findAllNotificationValidByReceiver(UUID uuidReceiver);

}
