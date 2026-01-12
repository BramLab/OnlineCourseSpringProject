package org.intecbrussel.onlinecoursespringproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for testing (Cross-Site Request Forgery)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // Allow public access to auth endpoints
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        //.requestMatchers("/api/admin/**").permitAll() // TEMPORARY - for testing only
                        .requestMatchers("/api/courses/**").permitAll() // Public course viewing
                        .anyRequest().authenticated() // Require authentication for other endpoints
                );

        return http.build();
    }

}
