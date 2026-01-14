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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

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
    public CourseResponse updateCourse(long id, CourseRequest courseRequest) {
        if (id != courseRequest.id()) {
            throw new MissingDataException("The id of the requested course does not match.");
        }

        Course course = getCourseIfLoggedInUserIsAllowed(courseRequest.id());

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

    private Course getCourseIfLoggedInUserIsAllowed(long courseId) {
        // Find currently logged-in user.
        String currentUsernameFromSecurityContext;
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            currentUsernameFromSecurityContext = authentication.getName();
        }catch(Exception e){
            throw new UnauthorizedActionException("You are not authenticated. Login (again).");
        }
        Optional<User> optionalUser = userRepository.findByUsername(currentUsernameFromSecurityContext);
        if(optionalUser.isEmpty() || optionalUser.get().getRole() != Role.INSTRUCTOR ){
            throw new UnauthorizedActionException("User not found, or you do not have the role instructor.");
        }

        // Find course to be updated & check if current user is author of it.
        Optional<Course> optionalCourse = courseRepository.findById(courseId);
        if(optionalCourse.isEmpty() || !Objects.equals(optionalCourse.get().getInstructor(), optionalUser.get())){
            throw new ResourceNotFoundException("Course not found, or you are not the instructor of this course.");
        }
        Course course = optionalCourse.get();
        return course;
    }

    @Override
    public void deleteCourse(long courseId) {
        Course course = getCourseIfLoggedInUserIsAllowed(courseId);
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
