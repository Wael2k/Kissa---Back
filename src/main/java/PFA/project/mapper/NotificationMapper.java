package PFA.project.mapper;

import PFA.project.dao.entity.Notification;
import PFA.project.dto.notificationDto.NotificationDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    List<NotificationDto> listNotificationsToListNotificationsDto (List<Notification> notifications);

}
