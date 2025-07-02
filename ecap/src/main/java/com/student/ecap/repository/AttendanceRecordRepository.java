package com.student.ecap.repository;

import com.student.ecap.model.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the AttendanceRecord entity.
 */
@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Integer> {

    /**
     * Finds an attendance record for a specific session and student.
     * @param sessionId ID of the attendance session.
     * @param studentRollNumber Roll number of the student.
     * @return Optional containing the AttendanceRecord if found.
     */
    Optional<AttendanceRecord> findByAttendanceSession_IdAndStudent_RollNumber(Integer sessionId, String studentRollNumber);

    /**
     * Finds all attendance records for a specific attendance session.
     * @param sessionId ID of the attendance session.
     * @return List of AttendanceRecord entities.
     */
    List<AttendanceRecord> findByAttendanceSession_Id(Integer sessionId);

    /**
     * Finds all attendance records for a specific student.
     * @param studentRollNumber Roll number of the student.
     * @return List of AttendanceRecord entities.
     */
    List<AttendanceRecord> findByStudent_RollNumber(String studentRollNumber);

    /**
     * Finds all attendance records for a specific student within a course and semester.
     * This requires joining through attendanceSession.
     * @param studentRollNumber Roll number of the student.
     * @param courseId ID of the course.
     * @param semesterId ID of the semester.
     * @return List of AttendanceRecord entities.
     */
    List<AttendanceRecord> findByStudent_RollNumberAndAttendanceSession_Course_CourseIdAndAttendanceSession_Semester_SemesterId(
            String studentRollNumber, Integer courseId, Integer semesterId);
}
