package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.UserCreateDto;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponseDto;
import org.intecbrussel.onlinecoursespringproject.model.Role;

import java.util.List;

// create, update, delete, getById, getAll, (getByRole)
public interface UserService {
    UserResponseDto createUser(UserCreateDto userCreateDto);
    UserResponseDto getUserById(long id);
    List<UserResponseDto> getAllUsers();
    List<UserResponseDto> getUsersByRole(Role role);
    UserResponseDto updateUser(Long id, UserCreateDto userCreateDto);
    void deleteUser(Long id);
}
