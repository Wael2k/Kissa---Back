package PFA.project.dao;

import PFA.project.dao.entity.SocketIoSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SocketIoSessionRepository extends JpaRepository<SocketIoSession,Long> {
    List<SocketIoSession> getAllByClient(UUID clientUuid);
    Optional<SocketIoSession> getByClient(UUID clientUuid);

    List<SocketIoSession> getAllBySession(UUID clientUuid);

    Optional<SocketIoSession> getBySession(UUID sessionUuid);
    Optional<SocketIoSession> getByClientAndSession(UUID clientUuid,UUID sessionUuid);


}
