package com.student.ecap.repository;

import com.student.ecap.model.AttendanceSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the AttendanceSession entity.
 */
@Repository
public interface AttendanceSessionRepository extends JpaRepository<AttendanceSession, Integer> {

    /**
     * Finds an attendance session by its defining attributes (for checking uniqueness).
     * @param teacherId ID of the teacher.
     * @param courseId ID of the course.
     * @param branchId ID of the branch.
     * @param semesterId ID of the semester.
     * @param periodId ID of the period.
     * @param dayId ID of the day.
     * @param date The actual date of the session.
     * @return Optional containing the AttendanceSession if found.
     */
    Optional<AttendanceSession> findByTeacher_IdAndCourse_CourseIdAndBranch_BranchIdAndSemester_SemesterIdAndPeriod_IdAndDay_IdAndDate(
            Integer teacherId, Integer courseId, Integer branchId, Integer semesterId, Integer periodId, Integer dayId, LocalDate date);

    /**
     * Finds all attendance sessions for a specific student, course, and date range.
     * This query is more complex as it needs to join through AttendanceRecord.
     * For now, we'll focus on getting sessions by class details.
     *
     * Example: Find all sessions for a given branch, semester, course, and teacher.
     * @param branchId
     * @param semesterId
     * @param courseId
     * @param teacherId
     * @return
     */
    List<AttendanceSession> findByBranch_BranchIdAndSemester_SemesterIdAndCourse_CourseIdAndTeacher_Id(
            Integer branchId, Integer semesterId, Integer courseId, Integer teacherId);

    /**
     * Finds all attendance sessions for a specific branch, semester, and date.
     * @param branchId ID of the branch.
     * @param semesterId ID of the semester.
     * @param date The actual date of the session.
     * @return List of AttendanceSession entities.
     */
    List<AttendanceSession> findByBranch_BranchIdAndSemester_SemesterIdAndDate(Integer branchId, Integer semesterId, LocalDate date);
}
