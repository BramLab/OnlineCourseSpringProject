package org.intecbrussel.onlinecoursespringproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
//@NoArgsConstructor
@AllArgsConstructor
@Data //Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@Entity
@NoArgsConstructor
public class Enrollment extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User student;

    @ManyToOne
    private Course course;

}
