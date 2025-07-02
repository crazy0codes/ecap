package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'attendance_record' table in the database.
 * Stores the attendance status for an individual student in a specific session.
 */
@Entity
@Table(name = "attendance_record", uniqueConstraints = {
        // Unique constraint to prevent a student from having multiple records for the same session
        @UniqueConstraint(columnNames = {"attendance_session_id", "student_roll_number"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Many-to-one relationship with the AttendanceSession entity.
     * Links this record to a specific attendance session.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendance_session_id", referencedColumnName = "id", nullable = false)
    private AttendanceSession attendanceSession;

    /**
     * Many-to-one relationship with the Student entity.
     * The student whose attendance is being recorded.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_roll_number", referencedColumnName = "roll_number", nullable = false)
    private Student student;

    /**
     * The attendance status (e.g., "Present", "Absent").
     */
    @Column(name = "status", length = 20, nullable = false)
    private String status; // Consider making this an enum if statuses are fixed
}
