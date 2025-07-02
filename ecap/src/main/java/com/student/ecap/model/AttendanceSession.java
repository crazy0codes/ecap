package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Represents the 'attendance_session' table in the database.
 * Records a specific instance of a class session for which attendance is taken.
 */
@Entity
@Table(name = "attendance_session", uniqueConstraints = {
        // Unique constraint to prevent duplicate sessions for the exact same class slot on the same date
        @UniqueConstraint(columnNames = {"teacher_id", "course_id", "branch_id", "semester_id", "period_id", "day_id", "date"})
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Many-to-one relationship with the Teacher entity.
     * The teacher conducting this session.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", nullable = false) // Corrected referencedColumnName to 'id'
    private Teacher teacher;

    /**
     * Many-to-one relationship with the Course entity.
     * The course being taught in this session.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;

    /**
     * Many-to-one relationship with the Branch entity.
     * The branch for which this session is held.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = false)
    private Branch branch;

    /**
     * Many-to-one relationship with the Semester entity.
     * The semester for which this session is held.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id", nullable = false)
    private Semester semester;

    /**
     * Many-to-one relationship with the Period entity.
     * The period number of this session.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", referencedColumnName = "id", nullable = false)
    private Period period;

    /**
     * Many-to-one relationship with the Day entity.
     * The day of the week for this session.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", referencedColumnName = "id", nullable = false)
    private Day day;

    /**
     * The actual calendar date of the attendance session.
     */
    @Column(name = "date", nullable = false)
    private LocalDate date;
}
