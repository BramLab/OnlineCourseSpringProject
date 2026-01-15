package org.intecbrussel.onlinecoursespringproject.repository;

import org.intecbrussel.onlinecoursespringproject.model.Enrollment;
import java.util.List;


public interface CustomEnrollmentRepository {

    List<Enrollment> findForStudent(long studentId);

    List<Enrollment> findForInstructor(long instructorId);
}
