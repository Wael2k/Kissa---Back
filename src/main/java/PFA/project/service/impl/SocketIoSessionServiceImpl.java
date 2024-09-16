package PFA.project.service.impl;

import PFA.project.dao.SocketIoSessionRepository;
import PFA.project.dao.entity.SocketIoSession;
import PFA.project.enums.NotificationContextEnum;
import PFA.project.service.SocketIoSessionService;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class SocketIoSessionServiceImpl implements SocketIoSessionService {
    @Autowired
    private SocketIoSessionRepository socketIoSessionRepository;


    @Override
    public SocketIoSession createOrUpdate(SocketIoSession socketIoSession) {
        return this.socketIoSessionRepository.saveAndFlush(socketIoSession);
    }

    @Override
    public List<SocketIoSession> getAllByClientUuid(UUID clientUuid) {
        return this.socketIoSessionRepository.getAllByClient(clientUuid);
    }

    @Override
    public Optional<SocketIoSession> getByClientUuid(UUID clientUuid) {
        return socketIoSessionRepository.getByClient(clientUuid);
    }

    @Override
    public Optional<SocketIoSession> getBySessionUuid(UUID clientUuid) {
        return this.socketIoSessionRepository.getBySession(clientUuid);

    }

    @Override
    public Optional<SocketIoSession> getByClientUuidAndSessionUuid(UUID clientUuid, UUID sessionUuid) {
        return this.socketIoSessionRepository.getByClientAndSession(clientUuid,sessionUuid);
    }



    @Override
    public void deleteSessions(List<SocketIoSession> socketIoSessions) {
        this.socketIoSessionRepository.deleteAll(socketIoSessions);
    }


}
