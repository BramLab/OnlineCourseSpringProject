package org.intecbrussel.onlinecoursespringproject.configuration;

import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return String[] args -> {

            //String userName, String email, Role role, String passwordHashed
            User instructor = new User(0, "user1", "a.b@c.com", Role.INSTRUCTOR);
            userRepository.save(instructor);
        }
    }

}
