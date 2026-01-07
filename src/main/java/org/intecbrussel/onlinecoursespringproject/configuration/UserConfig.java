package org.intecbrussel.onlinecoursespringproject.configuration;

import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

//@SpringBootApplication = @Configuration, @EnableAutoConfiguration and @ComponentScan
@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            User instr1 = new User();
            instr1.setUserName("user1");
            instr1.setEmail("user1@c.com");
            instr1.setRole(Role.INSTRUCTOR);
            instr1.setPasswordHashed(clearTextToSHA256HashToBase64("1234"));
            userRepository.save(instr1);

            User instr2 = new User(0, "user2", "user2@c.com", Role.INSTRUCTOR
                    , clearTextToSHA256HashToBase64("5678"));
            userRepository.save(instr2);
        };
    }

    //Base64 encoded SHA-256 hash
    public static String clearTextToSHA256HashToBase64(final String clearText) throws NoSuchAlgorithmException {
        byte[] clearTextBytes = clearText.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] digestedMessage = messageDigest.digest(clearTextBytes);
        return new String( Base64.getEncoder().encode(digestedMessage));
    }

}
