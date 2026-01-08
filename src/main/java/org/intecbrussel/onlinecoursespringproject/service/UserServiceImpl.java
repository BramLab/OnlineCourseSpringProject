package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import org.intecbrussel.onlinecoursespringproject.dto.UserCreateUpdateDto;
import org.intecbrussel.onlinecoursespringproject.dto.UserMapper;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponseDto;
import org.intecbrussel.onlinecoursespringproject.exception.NotFoundException;
import org.intecbrussel.onlinecoursespringproject.exception.ResourceNotFoundException;
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
    public UserResponseDto createUser(UserCreateUpdateDto userCreateUpdateDto) {
        User user = new User();
        if (userCreateUpdateDto.userName() != null) user.setUserName(userCreateUpdateDto.userName());
        if (userCreateUpdateDto.email() != null) user.setEmail(userCreateUpdateDto.email());
        if (userCreateUpdateDto.role() != null) user.setRole(userCreateUpdateDto.role());
        if (userCreateUpdateDto.password() != null) {
            user.setPasswordHashed(passwordEncoder.encode(userCreateUpdateDto.password()));
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
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
