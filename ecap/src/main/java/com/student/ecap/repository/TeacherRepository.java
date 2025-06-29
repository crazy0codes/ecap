package com.student.ecap.repository;

import com.student.ecap.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Teacher entity.
 * Provides standard CRUD operations for Teacher objects.
 */
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
