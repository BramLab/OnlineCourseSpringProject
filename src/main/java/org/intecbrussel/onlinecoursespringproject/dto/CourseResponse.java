package org.intecbrussel.onlinecoursespringproject.dto;

import jakarta.persistence.*;
import org.intecbrussel.onlinecoursespringproject.model.User;

import java.util.Date;

public record CourseResponse(
        long  id,
        String title,
        String description,
        UserResponse instructor,
        Date startDate,
        Date endDate
) {}
