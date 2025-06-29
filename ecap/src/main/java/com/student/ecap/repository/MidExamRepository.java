package com.student.ecap.repository;

import com.student.ecap.model.MidExam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MidExam entity.
 * Provides standard CRUD operations for MidExam objects.
 */
@Repository
public interface MidExamRepository extends JpaRepository<MidExam, Integer> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
