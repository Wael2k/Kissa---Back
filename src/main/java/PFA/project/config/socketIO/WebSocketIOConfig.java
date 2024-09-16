package PFA.project.config.socketIO;

import PFA.project.config.socketIO.listeners.ConnectListener;
import PFA.project.config.socketIO.listeners.DisconnectListener;
import PFA.project.config.socketIO.listeners.OnConnectListenerHandler;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebSocketIOConfig {
    @Autowired
    private ConnectListener connectListener;
    @Autowired
    private DisconnectListener disconnectListener;
    @Autowired
    private OnConnectListenerHandler onConnectListenerHandler;
    @Bean
    public SocketIOServer serverConfig() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname("localhost");
        config.setPort(9990);
        config.setHttpCompression(false);
        config.setWebsocketCompression(false);
        config.setAuthorizationListener(this.onConnectListenerHandler);
        SocketIOServer socketIOServer = new SocketIOServer(config);
        socketIOServer.addConnectListener(this.connectListener);
        socketIOServer.addDisconnectListener(this.disconnectListener);
        return socketIOServer;
    }
}
