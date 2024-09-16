package PFA.project.event.createUser;
import PFA.project.enums.TypeRegisterEnum;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import java.util.UUID;
@Component
public class CreatingUserPublisher implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;    }
    public void publish(String email , UUID uuid , String password, TypeRegisterEnum typeRegisterEnum) {
        this.publisher.publishEvent(new CreatingUser(this,email,password,uuid, typeRegisterEnum));
    }
}
