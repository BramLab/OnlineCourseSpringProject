package org.intecbrussel.onlinecoursespringproject.dto;

import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.User;

public record EnrollmentResponse (
        long id,
        CourseResponse courseResponse,
        UserResponse userResponse
){}
