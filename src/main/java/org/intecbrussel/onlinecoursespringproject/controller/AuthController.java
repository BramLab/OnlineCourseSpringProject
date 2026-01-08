package org.intecbrussel.onlinecoursespringproject.controller;

import org.intecbrussel.onlinecoursespringproject.dto.UserCreateUpdateDto;
import org.intecbrussel.onlinecoursespringproject.dto.UserResponseDto;
import org.intecbrussel.onlinecoursespringproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDto saveEmployee(@RequestBody UserCreateUpdateDto userCreateUpdateDto) {
        return userService.createUser(userCreateUpdateDto);
    }

//    @PutMapping("/{id}")
//    public UserResponseDto updateEmployee(@PathVariable Long id,@RequestBody UserCreateUpdateDto userCreateUpdateDto) {
//        if(!Objects.equals( id, userCreateUpdateDto.id() )){
//            throw new IllegalArgumentException("IDs don't match");
//        }
//        return userService.updateUser(id, userCreateUpdateDto);
//    }

}
