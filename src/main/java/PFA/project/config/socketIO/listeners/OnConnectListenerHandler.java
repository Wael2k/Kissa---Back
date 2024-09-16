package PFA.project.config.socketIO.listeners;

import PFA.project.config.security.config.JwtConfig;
import PFA.project.config.utils.JwtUtil;
import com.corundumstudio.socketio.AuthorizationListener;
import com.corundumstudio.socketio.AuthorizationResult;
import com.corundumstudio.socketio.HandshakeData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OnConnectListenerHandler implements AuthorizationListener {
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private UserDetailsService userDetailsService;


    public AuthorizationResult getAuthorizationResult(HandshakeData handshakeData) {
        String authorization = handshakeData.getSingleUrlParam("token");
        String token = authorization.replace("Bearer ", "");
        boolean validBearerHeader = JwtUtil.isTokenExpired(token, this.jwtConfig.getSigningKey());
        if (validBearerHeader) {
            //Todo this method should be changed as an event.
            String userName = JwtUtil.extractUsername(token,jwtConfig.getSigningKey());
            UserDetails user = userDetailsService.loadUserByUsername(userName);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
            return AuthorizationResult.SUCCESSFUL_AUTHORIZATION;
        }
        return AuthorizationResult.FAILED_AUTHORIZATION;
    }
}
