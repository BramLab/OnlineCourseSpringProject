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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String userName;
    private String email;
    private Role role;
    private String passwordHashed;

    public User(int i, String user1, String mail, Role role) {
    }

//    User(String userName, String email, Role role, String passwordHashed) {
//        
//        this.userName = userName;
//        this.email = email;
//        this.role = role;
//        this.passwordHashed = passwordHashed;
//    }

}
