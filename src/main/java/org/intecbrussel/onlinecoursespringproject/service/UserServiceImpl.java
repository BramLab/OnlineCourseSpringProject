package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.UserChangeRoleRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserMapper;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.exception.ResourceNotFoundException;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> UserMapper.mapToUserResponse(u))
                //.map(UserMapper::mapToUserResponse) //method reference not intuitive yet
                .toList();
    }

    @Override
    public UserResponse updateUserChangeRole(Long id, UserChangeRoleRequest userChangeRoleRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        user.setRole(userChangeRoleRequest.role());
        User updatedUser = userRepository.save(user);
        return UserMapper.mapToUserResponse(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
