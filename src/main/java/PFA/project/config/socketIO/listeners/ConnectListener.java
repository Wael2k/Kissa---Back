package PFA.project.config.socketIO.listeners;

import PFA.project.dao.entity.SocketIoSession;
import PFA.project.dao.entity.UserInfo;
import PFA.project.service.SocketIoSessionService;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class ConnectListener implements com.corundumstudio.socketio.listener.ConnectListener {
    @Autowired
    private SocketIoSessionService socketIoSessionService;
    @Override
    public void onConnect(SocketIOClient socketIOClient) {
        UUID clientUuid = ((UserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUuid();
         socketIoSessionService.getBySessionUuid(socketIOClient.getSessionId()).map(socketIoSession -> {
            socketIoSession.setClient(clientUuid);
            return socketIoSessionService.createOrUpdate(socketIoSession);
        }).orElseGet(() -> {
            SocketIoSession socketIoSession = new SocketIoSession();
            socketIoSession.setClient(clientUuid);
            socketIoSession.setSession(socketIOClient.getSessionId());

            return socketIoSessionService.createOrUpdate(socketIoSession);
        });
        log.info("Client {} connected successfully",clientUuid);


    }
}
