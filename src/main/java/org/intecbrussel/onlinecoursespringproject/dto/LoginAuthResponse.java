package org.intecbrussel.onlinecoursespringproject.dto;

import org.intecbrussel.onlinecoursespringproject.model.Role;

public record LoginAuthResponse(
        long id,
        String userName,
        String email,
        Role role,
        String token
) {}
