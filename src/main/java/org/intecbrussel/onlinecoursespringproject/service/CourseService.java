package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.CourseResponse;
import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.User;
import java.util.Date;
import java.util.List;

public interface CourseService {
    CourseResponse createCourse(String title, String description, long userId, Date startDate, Date endDate);
    // update change instructor - not allowed in this version (could be abused?).
    Course updateCourse(long id, String title, String description, Date startDate, Date endDate);
    void deleteCourse(long id);
    List<Course> findAllCourses();
}
