package org.intecbrussel.onlinecoursespringproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.intecbrussel.onlinecoursespringproject.model.Role;

public record RegisterRequest(
    long id,
    @NotBlank(message = "Username is required")
    String userName,

    @NotBlank(message = "Email is required")
    String email,

    @NotNull(message = "Role is required")
    Role role,

    @NotBlank(message = "Password is required")
    String password
){}
