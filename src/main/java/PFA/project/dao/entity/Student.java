package PFA.project.dao.entity;

import PFA.project.persistence.dao.entity.AbstractBaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "TABLE_STUDENT")
@Data
public class Student extends AbstractBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private UUID uuid;
    private String fullName;
    private String email;
    private String level;
    private String school;
    private long  age;
    private String ville;
    private String parentPhone;
    private String parentName;


    @PrePersist
    private void generateUUID() {
        this.uuid = UUID.randomUUID();
    }
}
