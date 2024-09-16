package PFA.project.persistence.dao.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import java.util.Date;

@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class AbstractBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @CreatedDate
    @Column(name = "CREATED", updatable = false)
    private Date created;


    @LastModifiedDate
    @Column(name = "MODIFIED")
    private Date modified;

    @Transient
    @Column(name = "VERSION")
    private int version;

}