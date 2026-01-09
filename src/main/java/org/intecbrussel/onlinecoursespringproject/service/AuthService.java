package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.*;

import java.util.List;

// create, update, delete, getById, getAll, (getByRole)
public interface AuthService {
    UserResponse registerUser(RegisterRequest registerRequest);
    LoginAuthResponse login(LoginAuthRequest loginAuthRequest);
    List<UserResponse> getAllUsers();
    UserResponse updateUserChangeRole(Long id, UserChangeRoleRequest userChangeRoleRequest);
    void deleteUser(Long id);

    //    UserResponse getUserById(long id);
    //    List<UserResponse> getUsersByRole(Role role);

}
