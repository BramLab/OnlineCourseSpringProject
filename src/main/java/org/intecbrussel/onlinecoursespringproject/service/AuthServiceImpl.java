package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import org.intecbrussel.onlinecoursespringproject.dto.*;
import org.intecbrussel.onlinecoursespringproject.exception.DuplicateEnrollmentException;
import org.intecbrussel.onlinecoursespringproject.exception.ResourceNotFoundException;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponse registerUser(RegisterRequest registerRequest) {
        User user = new User();
        if (registerRequest.userName() != null) user.setUsername(registerRequest.userName());
        User similarOriginalUser = userRepository.findByUsername(registerRequest.userName())
                .ifPresentOrElse((u) -> {
                    throw new DuplicateEnrollmentException("Username already exists.");
                }, ()->{;});

        if (registerRequest.email() != null) user.setEmail(registerRequest.email());
        // todo check email exists
        if (registerRequest.role() != null) user.setRole(registerRequest.role());
        if (registerRequest.password() != null) {
            user.setPasswordHashed(passwordEncoder.encode(registerRequest.password()));
        }

        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

    @Override
    public LoginAuthResponse login(LoginAuthRequest loginAuthRequest) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public UserResponse updateUserChangeRole(Long id, UserChangeRoleRequest userChangeRoleRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setRole(userChangeRoleRequest.role());
        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
