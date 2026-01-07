package org.intecbrussel.onlinecoursespringproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data //Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@Entity
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String email;
    private Role role;
    private String passwordHashed;

}
