package org.intecbrussel.onlinecoursespringproject.dto;

import org.intecbrussel.onlinecoursespringproject.model.Enrollment;

public class EnrollmentMapper {

    public static EnrollmentResponse mapToEnrollmentResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                new CourseResponse(
                        enrollment.getCourse().getId(),
                        enrollment.getCourse().getTitle(),
                        enrollment.getCourse().getDescription(),
                        null, // too deep
                        enrollment.getCourse().getStartDate(),
                        enrollment.getCourse().getEndDate()
                ),
                new UserResponse(
                        enrollment.getStudent().getId(),
                        enrollment.getStudent().getUsername(),
                        enrollment.getStudent().getEmail(),
                        enrollment.getStudent().getRole()
                )
        );
    }

}
