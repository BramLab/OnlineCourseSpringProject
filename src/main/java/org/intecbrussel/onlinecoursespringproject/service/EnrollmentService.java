package org.intecbrussel.onlinecoursespringproject.service;

import org.intecbrussel.onlinecoursespringproject.dto.EnrollmentResponse;

import java.util.List;

public interface EnrollmentService {
    EnrollmentResponse createEnrollment(long courseId, long studentId);//studentId redundant if user is student

    //student own, instructor enrolled students, admin all.
    List<EnrollmentResponse> listEnrollments();

    void cancelEnrollment(long enrollmentId);
}
