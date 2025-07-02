package com.student.ecap.repository;

import com.student.ecap.model.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Grade entity.
 * Provides standard CRUD operations for Grade objects.
 */
@Repository
public interface GradeRepository extends JpaRepository<Grade, String> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}