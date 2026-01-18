package org.intecbrussel.onlinecoursespringproject.model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data //Combines @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@Entity
public class User extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;

    // https://stackoverflow.com/questions/67825729/using-enums-in-a-spring-entity
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private Role role;
    private String passwordHashed;
}
