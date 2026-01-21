package org.intecbrussel.onlinecoursespringproject.controller;

import lombok.RequiredArgsConstructor;
import org.intecbrussel.onlinecoursespringproject.dto.EnrollmentResponse;
import org.intecbrussel.onlinecoursespringproject.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    //EnrollmentController
    //Methode Endpoint Auth
    //POST    /api/courses/{id}/enroll    STUDENT/ADMIN           08
    //GET     /api/enrollments/me         STUDENT                 09
    //GET     /api/instructor/enrollments INSTRUCTOR              10
    //GET     /api/admin/enrollments      ADMIN                   11
    //DELETE /api/enrollments/{id}        STUDENT (self) / ADMIN  12

    @PostMapping("/courses/{course_id}/enroll/{student_id}")
    public EnrollmentResponse enrollStudentAsAdmin(@PathVariable Long course_id, @PathVariable Long student_id) {
        return enrollmentService.createEnrollmentForStudent(course_id, student_id);
    }

    @PostMapping("/courses/{course_id}/enroll")
    public EnrollmentResponse enrollSelf(@PathVariable Long course_id) {
        return enrollmentService.createEnrollmentForLoggedInUser(course_id);
    }

    @GetMapping("/instructor/enrollments")
    public List<EnrollmentResponse> getEnrollmentsForInstructor() {
        return enrollmentService.listEnrollments();
    }

    @GetMapping("/enrollments/me")
    public List<EnrollmentResponse> getEnrollmentsForStudent() {
        return enrollmentService.listEnrollments();
    }

    @GetMapping("/admin/enrollments")
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentService.listEnrollments();
    }

    @DeleteMapping("/enrollments/{enrollmentId}")
    public void cancelEnrollment(@PathVariable Long enrollmentId) {
        enrollmentService.cancelEnrollment(enrollmentId);
    }

}
