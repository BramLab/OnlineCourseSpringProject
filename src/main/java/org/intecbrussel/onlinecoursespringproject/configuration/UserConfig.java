package org.intecbrussel.onlinecoursespringproject.configuration;

import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@SpringBootApplication = @Configuration, @EnableAutoConfiguration and @ComponentScan
@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return String[] args -> {
            User instr1 = new User();
            instr1.setUserName("user1");
            instr1.setEmail("a.b@c.com");
            instr1.setRole(Role.INSTRUCTOR);
            instr1.setPasswordHashed("123");

            //User instructor = new User(0, "user1", "a.b@c.com", Role.INSTRUCTOR);
            //userRepository.save(instr1);
        };
    }

}
