package PFA.project.dao.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TABLE_SOCKET_IO_SESSION")
@Data
public class SocketIoSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "SESSION")
    private UUID session;
    @Column(name = "CLIENT",unique = true)
    private UUID client;



}
