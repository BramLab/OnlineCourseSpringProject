package org.intecbrussel.onlinecoursespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.EnrollmentResponse;
import org.intecbrussel.onlinecoursespringproject.model.Enrollment;
import org.intecbrussel.onlinecoursespringproject.repository.CustomEnrollmentRepository;
import org.intecbrussel.onlinecoursespringproject.repository.EnrollmentRepository;
import org.intecbrussel.onlinecoursespringproject.service.EnrollmentService;
import org.intecbrussel.onlinecoursespringproject.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

//@PreAuthorize to lock endpoints by role.

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    //private final CustomEnrollmentRepository customEnrollmentRepository;
    private final UserService userService;
    private final EnrollmentRepository enrollmentRepository;

    //Methode Endpoint Auth
    //POST /api/courses/{id}/enroll STUDENT/ADMIN
    //GET /api/enrollments/me STUDENT
    //GET /api/instructor/enrollments INSTRUCTOR
    //GET /api/admin/enrollments ADMIN
    //DELETE /api/enrollments/{id} STUDENT (self) / ADMIN

    @PostMapping("/courses/{id}/enroll")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public EnrollmentResponse enroll(@PathVariable Long id, Authentication auth) {
        Long userId = Long.valueOf(auth.getName()); // adjust if your principal not ID
        return enrollmentService.createEnrollment(id, userId);
    }

    @GetMapping("/enrollments/me")
    @PreAuthorize("hasRole('STUDENT')")
    public List<Enrollment> getMyEnrollments() {
        long studentId = userService.getLoggedInUser().getId();
        return enrollmentRepository.findForStudent(studentId);
    }

    @GetMapping("/instructor/enrollments")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<Enrollment> getInstructorEnrollments() {
        long instructorId = userService.getLoggedInUser().getId();
        return enrollmentRepository.findForInstructor(instructorId);
    }


    // Mappings can probably be combined. No time to research. Later.
    //GET /api  /instructor/enrollments INSTRUCTOR
    @GetMapping("/instructor/enrollments")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<EnrollmentResponse> getEnrollmentsForInstructor() {
        return enrollmentService.listEnrollments();
    }
    //GET /api  /enrollments/me STUDENT
    @GetMapping("/enrollments/me")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<EnrollmentResponse> getEnrollmentsForStudent() {
        return enrollmentService.listEnrollments();
    }
    //GET /api  /admin/enrollments ADMIN
    @GetMapping("/admin/enrollments")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.listEnrollments();
    }


    @DeleteMapping("/enrollments/{enrollmentId}")
    @PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public void cancelEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(enrollmentId);
    }

}
