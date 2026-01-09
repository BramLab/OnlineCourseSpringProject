package org.intecbrussel.onlinecoursespringproject.dto;

import jakarta.validation.constraints.NotNull;
import org.intecbrussel.onlinecoursespringproject.model.Role;

public record UserChangeRoleRequest(
    @NotNull(message = "Id is required")
    long id,

    @NotNull(message = "Role is required")
    Role role

) {}
