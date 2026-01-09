package org.intecbrussel.onlinecoursespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.UserChangeRoleRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.service.AuthService;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;

    @PutMapping("/users/{id}/role")
    public UserResponse updateUserChangeRole(@PathVariable Long id, @RequestBody UserChangeRoleRequest userChangeRoleRequest) {
        if(!Objects.equals( id, userChangeRoleRequest.id() )){
            throw new IllegalArgumentException("IDs don't match");
        }
        // no right role?
        // no right access security?
        return authService.updateUserChangeRole(id, userChangeRoleRequest);
    }

}
