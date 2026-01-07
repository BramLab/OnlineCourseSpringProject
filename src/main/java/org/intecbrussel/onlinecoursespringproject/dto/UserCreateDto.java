package org.intecbrussel.onlinecoursespringproject.dto;

import jakarta.validation.constraints.NotBlank;
import org.intecbrussel.onlinecoursespringproject.model.Role;

public class UserCreateDto {
    @NotBlank private String userName;
    @NotBlank private String email;
    @NotBlank private Role role;
    @NotBlank private String password;
}
