package org.intecbrussel.onlinecoursespringproject.repository;

import org.intecbrussel.onlinecoursespringproject.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {;}
