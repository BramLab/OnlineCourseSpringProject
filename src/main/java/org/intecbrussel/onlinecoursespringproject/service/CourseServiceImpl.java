package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.*;
import org.intecbrussel.onlinecoursespringproject.exception.MissingDataException;
import org.intecbrussel.onlinecoursespringproject.exception.ResourceNotFoundException;
import org.intecbrussel.onlinecoursespringproject.exception.UnauthorizedActionException;
import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.CourseRepository;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor //only works when CourseRepository & UserRepository are final!
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public CourseResponse createCourse(CourseRequest courseRequest){
        Optional<User> optionalUser = userRepository.findById(courseRequest.instructorId());
        if(optionalUser.isEmpty() || optionalUser.get().getRole() != Role.INSTRUCTOR ){
            throw new ResourceNotFoundException("User not found, or this user is not an instructor.");
        }
        if(courseRequest.startDate()==null || courseRequest.endDate()==null ||
                courseRequest.endDate().before(courseRequest.startDate())){
            throw new MissingDataException("End date cannot be before start date.");
        }

        Course course = new Course(0, courseRequest.title(), courseRequest.description()
                , optionalUser.get(), courseRequest.startDate(), courseRequest.endDate());

        Course createdCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseResponse(createdCourse);
    }

    @Override
    public CourseResponse updateCourse(long courseId, CourseRequest courseRequest) {
        if (courseId != courseRequest.id()) {
            throw new MissingDataException("The id of the requested course does not match.");
        }

        User user = userService.getLoggedInUser();

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isEmpty()){
            throw new ResourceNotFoundException("Course not found.");
        }
        Course course = optionalCourse.get();

        if(!(   (user.getRole() == Role.ADMIN) ||
                ( user.getRole() == Role.INSTRUCTOR && user.equals(course.getInstructor()) )
        )){
            throw new UnauthorizedActionException("You must either have role ADMIN, " +
                    "or have role INSTRUCTOR and be author of this course.");
        }

        // Validations of new request
        if(courseRequest.startDate()==null || courseRequest.endDate()==null ||
                courseRequest.endDate().before(courseRequest.startDate())){
            throw new MissingDataException("End date cannot be before start date.");
        }

        course.setTitle(courseRequest.title());
        course.setDescription(courseRequest.description());
        course.setStartDate(courseRequest.startDate());
        course.setEndDate(courseRequest.endDate());

        Course updatedCourse = courseRepository.save(course);
        return CourseMapper.mapToCourseResponse(updatedCourse);
    }

    @Override
    public void deleteCourse(long courseId) {

        User user = userService.getLoggedInUser();
        if(user.getRole() != Role.ADMIN){
            throw new UnauthorizedActionException("You must have role ADMIN.");
        }

        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isEmpty()){
            throw new ResourceNotFoundException("Course not found.");
        }
        Course course = optionalCourse.get();
        courseRepository.delete(course);
    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(c -> CourseMapper.mapToCourseResponse(c))
                .toList();
    }

    @Override
    public CourseResponse getCourseById(long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        return CourseMapper.mapToCourseResponse(course);
    }

}
