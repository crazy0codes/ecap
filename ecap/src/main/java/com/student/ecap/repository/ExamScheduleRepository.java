package com.student.ecap.repository;

import com.student.ecap.model.ExamSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List; // Import List for returning multiple schedules

/**
 * Spring Data JPA repository for the ExamSchedule entity.
 * Provides standard CRUD operations for ExamSchedule objects.
 */
@Repository
public interface ExamScheduleRepository extends JpaRepository<ExamSchedule, Integer> {

    /**
     * Finds ExamSchedule by semester number and regulation ID.
     * Note: Hibernate will automatically generate the join for semester and regulation.
     * @param semesterNumber The number of the semester (e.g., 1, 2).
     * @param regulationId The ID of the regulation (e.g., "R18", "R20").
     * @return A list of ExamSchedule entities matching the criteria.
     */
    List<ExamSchedule> findBySemester_SemesterNumberAndRegulation_RegulationId(Integer semesterNumber, String regulationId);
}
