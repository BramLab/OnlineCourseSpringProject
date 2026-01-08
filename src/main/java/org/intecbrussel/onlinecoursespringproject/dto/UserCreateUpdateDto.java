package org.intecbrussel.onlinecoursespringproject.dto;

import jakarta.validation.constraints.NotBlank;
import org.intecbrussel.onlinecoursespringproject.model.Role;

public record UserCreateUpdateDto(
    long id,
    @NotBlank String userName,
    @NotBlank String email,
    Role role,
    @NotBlank String password
){}
