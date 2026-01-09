package org.intecbrussel.onlinecoursespringproject.controller;

import jakarta.validation.Valid;
import org.intecbrussel.onlinecoursespringproject.dto.LoginAuthRequest;
import org.intecbrussel.onlinecoursespringproject.dto.LoginAuthResponse;
import org.intecbrussel.onlinecoursespringproject.dto.RegisterRequest;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponse;
import org.intecbrussel.onlinecoursespringproject.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public UserResponse register(@Valid @RequestBody RegisterRequest registerRequest) {
        return authService.registerUser(registerRequest);
    }

    @PostMapping("/login")
    public LoginAuthResponse login(@Valid @RequestBody LoginAuthRequest loginAuthRequest) {
        return authService.login(loginAuthRequest);
    }

//    @PutMapping("/{id}")
//    public UserResponseDto updateEmployee(@PathVariable Long id,@RequestBody UserCreateUpdateDto userCreateUpdateDto) {
//        if(!Objects.equals( id, userCreateUpdateDto.id() )){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        return userService.updateUser(id, userCreateUpdateDto);
//    }

}
