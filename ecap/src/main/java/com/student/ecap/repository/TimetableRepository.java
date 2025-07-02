package com.student.ecap.repository;

import com.student.ecap.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the Timetable entity.
 */
@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {
    /**
     * Finds all timetable entries for a specific branch, semester, and day.
     * @param branchId The ID of the branch.
     * @param semesterId The ID of the semester.
     * @param dayId The ID of the day.
     * @return A list of Timetable entries.
     */
    List<Timetable> findByBranch_BranchIdAndSemester_SemesterIdAndDay_Id(Integer branchId, Integer semesterId, Integer dayId);
}
    