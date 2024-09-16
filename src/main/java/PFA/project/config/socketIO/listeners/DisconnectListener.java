package PFA.project.config.socketIO.listeners;


import PFA.project.config.security.config.JwtConfig;
import PFA.project.config.utils.JwtUtil;
import PFA.project.dao.entity.SocketIoSession;
import PFA.project.dao.entity.UserInfo;
import PFA.project.service.SocketIoSessionService;
import com.corundumstudio.socketio.SocketIOClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
public class DisconnectListener implements com.corundumstudio.socketio.listener.DisconnectListener {
    @Autowired
    private SocketIoSessionService socketIoSessionService;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public void onDisconnect(SocketIOClient socketIOClient) {
        String authorization = socketIOClient.getHandshakeData().getSingleUrlParam("token");
        String token = authorization.replace("Bearer ", "");
        String userName = JwtUtil.extractUsername(token,jwtConfig.getSigningKey());
        UserInfo user = (UserInfo) userDetailsService.loadUserByUsername(userName);
        List<SocketIoSession> socketIOClientSessions = socketIoSessionService.getAllByClientUuid(user.getUuid());
        if(!socketIOClientSessions.isEmpty()){
            socketIoSessionService.deleteSessions(socketIOClientSessions);
        }
        socketIOClient.disconnect();
        log.info("Client {} disconnected successfully",user.getUuid());
    }
}
