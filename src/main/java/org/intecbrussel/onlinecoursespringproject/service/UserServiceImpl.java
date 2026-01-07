package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import org.intecbrussel.onlinecoursespringproject.dto.UserCreateDto;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponseDto;
import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDto createUser(UserCreateDto userCreateDto) {
        return null;
    }

    @Override
    public UserResponseDto getUserById(long id) {
        return null;
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return List.of();
    }

    @Override
    public List<UserResponseDto> getUsersByRole(Role role) {
        return List.of();
    }

    @Override
    public UserResponseDto updateUser(Long id, UserCreateDto userCreateDto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {

    }
}
