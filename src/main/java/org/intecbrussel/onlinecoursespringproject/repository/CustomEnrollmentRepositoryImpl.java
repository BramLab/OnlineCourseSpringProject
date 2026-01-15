package org.intecbrussel.onlinecoursespringproject.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.intecbrussel.onlinecoursespringproject.model.Enrollment;

import java.util.List;
import java.util.Optional;

public class CustomEnrollmentRepositoryImpl implements CustomEnrollmentRepository {

    // https://kanakalakshmi.medium.com/understanding-custom-repository-creation-in-spring-boot-2c786d600a5b
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<Enrollment> findForStudent(long studentId) {
        String query = "select e from Enrollment e where e.student.id = :studentId";
        return entityManager.createQuery(query, Enrollment.class)
                .setParameter("studentId", studentId)
                .getResultList();
    }

    @Override
    public List<Enrollment> findForInstructor(long instructorId) {
//        select e.id, e.course_id, e.student_id from enrollment e where e.id in
//        (select e.id from enrollment e inner join course c on e.course_id = c.id
//        and c.instructor_id = :instructorId)
        String query = "select e from Enrollment e where e.id in " +
                "( select e2.id from Enrollment e2 " +
                "inner join Course c on c.id = e2.course.id " +
                "where c.instructor.id = :instructorId)";
        return entityManager.createQuery(query, Enrollment.class)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }

}
