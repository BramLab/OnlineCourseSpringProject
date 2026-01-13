package org.intecbrussel.onlinecoursespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.UserChangeRoleRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PutMapping("/users/{id}/role")
    public UserResponse updateUserChangeRole(@PathVariable Long id, @RequestBody UserChangeRoleRequest userChangeRoleRequest) {
        if(!Objects.equals( id, userChangeRoleRequest.id() )){
            throw new IllegalArgumentException("IDs don't match");
        }
        // no right role?
        // no right access security?
        return userService.updateUserChangeRole(id, userChangeRoleRequest);
    }

    @GetMapping("/users")
    public List<UserResponse> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

}
