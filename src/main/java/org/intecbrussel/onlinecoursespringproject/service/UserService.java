package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.RegisterRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.model.Role;

import java.util.List;

// create, update, delete, getById, getAll, (getByRole)
public interface UserService {
    UserResponse createUser(RegisterRequest userCreateDto);
    UserResponse getUserById(long id);
    List<UserResponse> getAllUsers();
    List<UserResponse> getUsersByRole(Role role);
    //UserResponseDto updateUser(Long id, UserCreateUpdateDto userCreateDto);
    void deleteUser(Long id);
}
