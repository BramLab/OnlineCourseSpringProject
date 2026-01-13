package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.intecbrussel.onlinecoursespringproject.dto.CourseMapper;
import org.intecbrussel.onlinecoursespringproject.dto.CourseResponse;
import org.intecbrussel.onlinecoursespringproject.exception.ResourceNotFoundException;
import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.CourseRepository;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    CourseRepository courseRepository;
    UserRepository userRepository;

    @Override
    public CourseResponse createCourse(String title, String description, long instructorId, Date startDate, Date endDate) throws BadRequestException {
        User user = userRepository.getReferenceById(instructorId);
        if(user==null || user.getRole() != Role.INSTRUCTOR ){
            throw new ResourceNotFoundException("User not found, or this user is not an instructor.");
        }
        if(startDate==null || endDate==null || endDate.before(startDate)){
            throw new BadRequestException("End date cannot be before start date.");
        }
        Course course = new Course(0, title, description, user, startDate, endDate);
        Course createdCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseResponse(createdCourse);
    }

    @Override
    public Course updateCourse(long id, String title, String description, Date startDate, Date endDate) {
        return null;
    }

    @Override
    public void deleteCourse(long id) {

    }

    @Override
    public List<Course> findAllCourses() {
        return List.of();
    }
}
