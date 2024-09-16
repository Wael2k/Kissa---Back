package PFA.project.event.createUser;

import PFA.project.config.authority.UserRole;
import PFA.project.dao.entity.UserInfo;
import PFA.project.enums.TypeRegisterEnum;
import PFA.project.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreatingUserHandler implements ApplicationListener<CreatingUser> {

    @Autowired
    private AuthService authService;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public void onApplicationEvent(CreatingUser event) {
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail(event.getEmail());
        userInfo.setPassword(encoder.encode(event.getPassword()));
        userInfo.setUuid(event.getUuid());
        if(TypeRegisterEnum.TEACHER.equals(event.getTypeRegisterEnum())){
            userInfo.setRole(UserRole.TEACHER);
            log.info("creating new user role TEACHER");
        }else {
            userInfo.setRole(UserRole.STUDENT);
            log.info("creating new user role STUDENT");
        }
        userInfo = authService.createOrUpdateUser(userInfo);
        log.trace("User with id {} created  at {}",userInfo.getId(),userInfo.getCreated());
    }
}
