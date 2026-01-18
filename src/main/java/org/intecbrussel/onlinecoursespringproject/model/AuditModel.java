package org.intecbrussel.onlinecoursespringproject.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import java.io.Serializable;
import java.util.Date;

@Data //Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public abstract class AuditModel implements Serializable {

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    @CreatedDate
    private Date createdAt;

    //@Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;
}
