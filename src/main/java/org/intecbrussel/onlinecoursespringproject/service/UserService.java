package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.UserChangeRoleRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.model.User;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse updateUserChangeRole(Long id, UserChangeRoleRequest userChangeRoleRequest);
    void deleteUser(Long id);
    //User getLoggedInUser();

    //    UserResponse getUserById(long id);
    //    List<UserResponse> getUsersByRole(Role role);
}
