package com.student.ecap.repository;

import com.student.ecap.model.MidMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the MidMarks entity.
 * Provides standard CRUD operations for MidMarks objects.
 * Uses the composite primary key class MidMarks.MidMarksId.
 */
@Repository
public interface MidMarksRepository extends JpaRepository<MidMarks, MidMarks.MidMarksId> {
    // JpaRepository automatically handles the composite primary key based on MidMarks.MidMarksId
    // You can add custom query methods here if needed, e.g.,
    // List<MidMarks> findByStudent_RollNumberAndSemester_SemesterId(String rollNumber, Integer semesterId);
}
