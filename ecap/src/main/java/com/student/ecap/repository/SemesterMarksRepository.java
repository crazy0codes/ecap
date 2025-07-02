package com.student.ecap.repository;

import com.student.ecap.model.SemesterMarks;
import com.student.ecap.model.SemesterMarks.SemesterMarksId; // Import the IdClass if still using it, otherwise EmbeddedId doesn't need this direct import
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the SemesterMarks entity.
 * Provides standard CRUD operations for SemesterMarks objects.
 * Uses the composite primary key class SemesterMarks.SemesterMarksId.
 */
@Repository
public interface SemesterMarksRepository extends JpaRepository<SemesterMarks, SemesterMarksId> {
    // JpaRepository automatically handles the composite primary key based on SemesterMarks.SemesterMarksId

    /**
     * Finds all SemesterMarks for a given student roll number and semester ID.
     * Spring Data JPA will automatically build the query based on method name.
     *
     * @param rollNumber The roll number of the student.
     * @param semesterId The ID of the semester.
     * @return A list of SemesterMarks entities.
     */
    List<SemesterMarks> findByStudent_RollNumberAndSemester_SemesterId(String rollNumber, Integer semesterId);
}
