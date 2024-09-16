package PFA.project.config.security.config;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@ToString
@Component
public class JwtConfig {
    @Value("${signingKey}")
    private String signingKey;

    @Value("${server.servlet.session.cookie.name}")
    private String cookieName;

    @Value("${access-key:access_id}")
    private String accessId;
    @Value("${role:role}")
    private String role;
    @Value("${jwt.expiration}")
    private String expiration;
    @Value("${type:typeRegister}")
    private String typeRegister;

    @Value("${loginLink}")
    private String loginLink;


}
