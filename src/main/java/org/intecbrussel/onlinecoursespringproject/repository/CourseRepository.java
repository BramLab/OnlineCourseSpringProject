package org.intecbrussel.onlinecoursespringproject.repository;

import org.intecbrussel.onlinecoursespringproject.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findById(long courseId);
}
