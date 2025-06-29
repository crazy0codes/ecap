package com.student.ecap.repository;


import com.student.ecap.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Semester entity.
 * Provides standard CRUD operations for Semester objects.
 */
@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}