package org.intecbrussel.onlinecoursespringproject.dto;

import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.Role;

public class CourseMapper {

    public static CourseResponse mapToCourseResponse(Course course) {

        return new CourseResponse(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                new UserResponse(course.getInstructor().getId(),
                        course.getInstructor().getUsername(),
                        course.getInstructor().getEmail(),
                        course.getInstructor().getRole()
                ),
                course.getStartDate(),
                course.getEndDate()
        );
    }

}
