package org.intecbrussel.onlinecoursespringproject.repository;

import org.intecbrussel.onlinecoursespringproject.model.Enrollment;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomEnrollmentRepository {

    List<Enrollment> findForStudent(long studentId);

    List<Enrollment> findForInstructor(long instructorId);
}
