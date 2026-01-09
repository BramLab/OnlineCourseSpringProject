package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import org.intecbrussel.onlinecoursespringproject.dto.RegisterRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserMapper;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
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
    public UserResponse createUser(RegisterRequest registerRequest) {
        User user = new User();
        if (registerRequest.userName() != null) user.setUserName(registerRequest.userName());
        if (registerRequest.email() != null) user.setEmail(registerRequest.email());
        if (registerRequest.role() != null) user.setRole(registerRequest.role());
        if (registerRequest.password() != null) {
            user.setPasswordHashed(passwordEncoder.encode(registerRequest.password()));
        }
        //throw new ResourceNotFoundException("We got this far...");
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDTO(savedUser);
    }

//    @Override
//    public UserResponseDto updateUser(Long id, UserCreateUpdateDto userCreateUpdateDto) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new NotFoundException("User not found with id: " + id));
//        UserCreateUpdateDtoToUser(userCreateUpdateDto, user);
//        User updatedUser = userRepository.save(user);
//        return UserMapper.mapToUserDTO(updatedUser);
//    }

    @Override
    public UserResponse getUserById(long id) {
        return null;
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return List.of();
    }

    @Override
    public List<UserResponse> getUsersByRole(Role role) {
        return List.of();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
