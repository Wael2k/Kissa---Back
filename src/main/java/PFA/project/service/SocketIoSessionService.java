package PFA.project.service;

import PFA.project.dao.SocketIoSessionRepository;
import PFA.project.dao.entity.SocketIoSession;
import PFA.project.enums.NotificationContextEnum;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SocketIoSessionService {
     SocketIoSession createOrUpdate(SocketIoSession socketIoSession);
     List<SocketIoSession> getAllByClientUuid(UUID clientUuid);
     Optional<SocketIoSession> getByClientUuid(UUID clientUuid);

     Optional<SocketIoSession> getBySessionUuid(UUID clientUuid);
     Optional<SocketIoSession> getByClientUuidAndSessionUuid(UUID clientUuid,UUID sessionUuid);
     void deleteSessions(List<SocketIoSession> socketIoSessions);

}
