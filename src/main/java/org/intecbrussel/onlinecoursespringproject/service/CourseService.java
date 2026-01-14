package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.CourseRequest;
import org.intecbrussel.onlinecoursespringproject.dto.CourseResponse;

import java.util.List;

public interface CourseService {
    CourseResponse createCourse(CourseRequest courseRequest);
    // update change instructor could also have been possible -
    // design choice to not allow it - each teacher his/her own course (could be abused?).
    CourseResponse updateCourse(long id, CourseRequest courseRequest);
    void deleteCourse(long id);
    List<CourseResponse> getAllCourses();
    CourseResponse getCourseById(long id);
}
