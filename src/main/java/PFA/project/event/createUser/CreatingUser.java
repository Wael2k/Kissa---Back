package PFA.project.event.createUser;

import PFA.project.enums.TypeRegisterEnum;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.util.UUID;
@Getter
public class CreatingUser extends ApplicationEvent {
    private final String email;
    private final String password;
    private final UUID uuid;
    private final TypeRegisterEnum typeRegisterEnum;
    public CreatingUser(Object source , String email , String password , UUID uuid, TypeRegisterEnum typeRegisterEnum) {
        super(source);
        this.email = email;
        this.password = password;
        this.uuid = uuid;
        this.typeRegisterEnum = typeRegisterEnum;
    }
}
