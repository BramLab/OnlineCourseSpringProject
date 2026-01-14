package org.intecbrussel.onlinecoursespringproject.configuration;

import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.Enrollment;
import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.CourseRepository;
import org.intecbrussel.onlinecoursespringproject.repository.EnrollmentRepository;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;

//@SpringBootApplication = @Configuration, @EnableAutoConfiguration and @ComponentScan
@Configuration
@EnableJpaAuditing
public class UserConfig {

    @Bean
    CommandLineRunner dataLoader_commandLineRunner(UserRepository userRepository,
                                                   CourseRepository courseRepository,
                                                   EnrollmentRepository enrollmentRepository,
                                                   PasswordEncoder passwordEncoder) {
        return args -> {

            User user_instructor1 = new User();
            user_instructor1.setUsername("user_instructor1");
            user_instructor1.setEmail("user_instructor1@c.com");
            user_instructor1.setRole(Role.INSTRUCTOR);
            user_instructor1.setPasswordHashed(passwordEncoder.encode("i1"));
            //user_instructor1.setPasswordHashed(clearTextToSHA256HashToBase64("i1"));
            userRepository.save(user_instructor1);

            User user_instructor2 = new User(0, "user_instructor2", "user_instructor2@c.com", Role.INSTRUCTOR
                    , passwordEncoder.encode("i2"));    //, clearTextToSHA256HashToBase64("i2"));
            userRepository.save(user_instructor2);

            User user_student1 = new User(0, "user_student1", "user_student1@c.com", Role.STUDENT
                    , passwordEncoder.encode("s1"));
            userRepository.save(user_student1);

            User user_student2 = new User(0, "user_student2", "user_student2@c.com", Role.STUDENT
                    , passwordEncoder.encode("s2"));
            userRepository.save(user_student2);

            User user_admin1 = new User(0, "user_admin1", "user_admin1@c.com", Role.ADMIN
                    , passwordEncoder.encode("a1"));
            userRepository.save(user_admin1);


            Course course_java = new Course(  0, "Java", "OOP, preparation for OCA", user_instructor1
                    , Date.valueOf("2026-04-01"), Date.valueOf("2026-09-01")  );
            courseRepository.save(course_java);
            Course course_tws = new Course(0, "Tws", "cv, brief, solliciteren", user_instructor2,
                    Date.valueOf("2026-09-02"), Date.valueOf("2026-10-01")  );
            courseRepository.save(course_tws);


            Enrollment enrollment1 = new Enrollment(0, user_student1, course_java);
            enrollmentRepository.save(enrollment1);
            Enrollment enrollment2 = new Enrollment(0, user_student2, course_tws);
            enrollmentRepository.save(enrollment2);

        };
    }

    //Base64 encoded SHA-256 hash
//    public static String clearTextToSHA256HashToBase64(final String clearText) throws NoSuchAlgorithmException {
//        byte[] clearTextBytes = clearText.getBytes(StandardCharsets.UTF_8);
//        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
//        byte[] digestedMessage = messageDigest.digest(clearTextBytes);
//        return new String( Base64.getEncoder().encode(digestedMessage));
//    }

}
