package com.student.ecap.repository;

import com.student.ecap.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Course entity.
 * Provides standard CRUD operations for Course objects.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
