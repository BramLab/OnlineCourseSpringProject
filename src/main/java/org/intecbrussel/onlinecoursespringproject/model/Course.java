package org.intecbrussel.onlinecoursespringproject.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data //Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@Entity
public class Course extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;

    private String title;
    private String description;

    @ManyToOne
    private User instructor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;
}
