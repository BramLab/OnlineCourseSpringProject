package org.intecbrussel.onlinecoursespringproject.dto;

public record EnrollmentResponse (
        long id,
        CourseResponse courseResponse,
        UserResponse userResponse
){}
