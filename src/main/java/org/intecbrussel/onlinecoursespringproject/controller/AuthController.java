package org.intecbrussel.onlinecoursespringproject.controller;

import jakarta.validation.Valid;
import org.intecbrussel.onlinecoursespringproject.dto.RegisterRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse saveEmployee(@Valid @RequestBody RegisterRequest registerRequest) {
        return userService.createUser(registerRequest);
    }

//    @PutMapping("/{id}")
//    public UserResponseDto updateEmployee(@PathVariable Long id,@RequestBody UserCreateUpdateDto userCreateUpdateDto) {
//        if(!Objects.equals( id, userCreateUpdateDto.id() )){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        return userService.updateUser(id, userCreateUpdateDto);
//    }

}
