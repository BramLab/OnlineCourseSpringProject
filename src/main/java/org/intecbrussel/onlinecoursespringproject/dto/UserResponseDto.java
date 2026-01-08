package org.intecbrussel.onlinecoursespringproject.dto;

import org.intecbrussel.onlinecoursespringproject.model.Role;

public record UserResponseDto (
    long id,
    String userName,
    String email,
    Role role
){}
