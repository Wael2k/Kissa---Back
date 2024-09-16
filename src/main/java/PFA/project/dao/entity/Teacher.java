package PFA.project.dao.entity;

import PFA.project.persistence.dao.entity.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TABLE_TEACHER")
@Data
public class Teacher extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String email;
    private String fullName;
    private String school;
    private String phone;
    private String educationLevel;
    private String ville;





    @PrePersist
    private void generateUUID() {
        this.uuid = UUID.randomUUID();
    }
}
