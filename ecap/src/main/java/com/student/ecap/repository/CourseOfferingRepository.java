package com.student.ecap.repository;

import com.student.ecap.model.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CourseOffering entity.
 * Provides standard CRUD operations for CourseOffering objects.
 */
@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Integer> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
