package com.student.ecap.repository;

import com.student.ecap.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Student entity.
 * Provides standard CRUD operations for Student objects.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
