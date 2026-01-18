package org.intecbrussel.onlinecoursespringproject.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.EnrollmentMapper;
import org.intecbrussel.onlinecoursespringproject.dto.EnrollmentResponse;
import org.intecbrussel.onlinecoursespringproject.exception.ResourceNotFoundException;
import org.intecbrussel.onlinecoursespringproject.exception.UnauthorizedActionException;
import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.intecbrussel.onlinecoursespringproject.model.Enrollment;
import org.intecbrussel.onlinecoursespringproject.model.Role;
import org.intecbrussel.onlinecoursespringproject.model.User;
import org.intecbrussel.onlinecoursespringproject.repository.CourseRepository;
import org.intecbrussel.onlinecoursespringproject.repository.EnrollmentRepository;
import org.intecbrussel.onlinecoursespringproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    //Enroll student in course (student self of admin)
    //• List enrollments:
    //o student ziet alleen eigen
    //o instructor ziet enrollments van eigen courses
    //o admin ziet alles
    //• Cancel enrollment (student self of admin)

    @Override
    @Transactional
    public EnrollmentResponse createEnrollment(long courseId, long studentId) {
        //Enroll student in course (student self of admin)
        User loggedInUser = userService.getLoggedInUser();

        User studentToEnroll = userRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));

        if(!(   (loggedInUser.getRole() == Role.ADMIN) ||
                (loggedInUser.getRole() == Role.STUDENT && loggedInUser.equals(studentToEnroll) )
        )){
            throw new UnauthorizedActionException("You must either have role ADMIN, " +
                    "or be STUDENT and be logged-in.");
        }

        Enrollment enrollment = enrollmentRepository.save(new Enrollment(0, studentToEnroll, course));
        return EnrollmentMapper.mapToEnrollmentResponse(enrollment);
    }

    //• List enrollments:
    //o student ziet alleen eigen
    //o instructor ziet enrollments van eigen courses
    //o admin ziet alles
    @Override
    @Transactional
    public List<EnrollmentResponse> listEnrollments() {
        User loggedInUser = userService.getLoggedInUser();
        List<EnrollmentResponse> enrollments = new ArrayList<>();

        if (loggedInUser.getRole() == Role.STUDENT) {
            enrollments = enrollmentRepository.findForStudent(loggedInUser.getId())
                    .stream()
                    .map(e -> EnrollmentMapper.mapToEnrollmentResponse(e))
                    .toList();
        }
        else if (loggedInUser.getRole() == Role.INSTRUCTOR) {
            enrollments = enrollmentRepository.findForInstructor(loggedInUser.getId())
                    .stream()
                    .map(e -> EnrollmentMapper.mapToEnrollmentResponse(e))
                    .toList();
        }
        else if (loggedInUser.getRole() == Role.ADMIN) {
            enrollments = enrollmentRepository.findAll()
                    .stream()
                    .map(e -> EnrollmentMapper.mapToEnrollmentResponse(e))
                    .toList();
        }

        return enrollments;
    }


    @Override
    public void cancelEnrollment(long enrollmentId) {
        User loggedInUser = userService.getLoggedInUser();

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Enrollment not found with id."));

        if(!(   (loggedInUser.getRole() == Role.ADMIN) ||
                (loggedInUser.getRole() == Role.STUDENT && loggedInUser.equals(enrollment.getStudent()) )
        )){
            throw new UnauthorizedActionException("You must either have role ADMIN, " +
                    "or be STUDENT and be logged-in.");
        }

        enrollmentRepository.delete(enrollment);
    }

}
