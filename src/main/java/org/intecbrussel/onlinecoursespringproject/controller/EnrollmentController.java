package org.intecbrussel.onlinecoursespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.EnrollmentResponse;
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
    private final UserService userService;
    private final EnrollmentRepository enrollmentRepository;

    //EnrollmentController
    //Methode Endpoint Auth
    //POST    /api/courses/{id}/enroll    STUDENT/ADMIN           08
    //GET     /api/enrollments/me         STUDENT                 09
    //GET     /api/instructor/enrollments INSTRUCTOR              10
    //GET     /api/admin/enrollments      ADMIN                   11
    //DELETE /api/enrollments/{id}        STUDENT (self) / ADMIN  12

    @PostMapping("/courses/{course_id}/enroll/{student_id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EnrollmentResponse enrollStudentAsAdmin(@PathVariable Long course_id, @PathVariable Long student_id) {
        return enrollmentService.createEnrollmentForStudent(course_id, student_id);
    }

    @PostMapping("/courses/{course_id}/enroll")
    //@PreAuthorize("hasRole('STUDENT')")
    public EnrollmentResponse enrollSelf(@PathVariable Long course_id) {
        return enrollmentService.createEnrollmentForLoggedInUser(course_id);
    }


    // These 3 mappings can probably be combined. No time to research. Later.
    //GET     /api/instructor/enrollments INSTRUCTOR              10
    @GetMapping("/instructor/enrollments")
    @PreAuthorize("hasRole('INSTRUCTOR')")
    public List<EnrollmentResponse> getEnrollmentsForInstructor() {
        return enrollmentService.listEnrollments();
    }

    //GET     /api/enrollments/me         STUDENT                 09
    @GetMapping("/enrollments/me")
    @PreAuthorize("hasRole('STUDENT')")
    public List<EnrollmentResponse> getEnrollmentsForStudent() {
        return enrollmentService.listEnrollments();
    }

    //GET     /api/admin/enrollments      ADMIN                   11
    @GetMapping("/admin/enrollments")
    @PreAuthorize("hasRole('ADMIN')")
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.listEnrollments();
    }



    @DeleteMapping("/enrollments/{enrollmentId}")
    //@PreAuthorize("hasAnyRole('STUDENT', 'ADMIN')")
    public void cancelEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(enrollmentId);
    }

}
