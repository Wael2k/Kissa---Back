package PFA.project.config.socketIO;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SocketIOServerRunner implements CommandLineRunner {
    @Autowired
    private  SocketIOServer server;
    @Override
    public void run(String... args) throws Exception {
        this.server.start();
        log.info("websocket server is running");
    }
}
