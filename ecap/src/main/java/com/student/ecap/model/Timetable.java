package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'timetable' table in the database.
 * Defines the schedule for a specific branch, semester, day, and period.
 */
@Entity
@Table(name = "timetable", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branch_id", "semester_id", "day_id", "period_id"}) // Ensure unique entries per slot
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Many-to-one relationship with the Branch entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id", nullable = false)
    private Branch branch;

    /**
     * Many-to-one relationship with the Semester entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id", nullable = false)
    private Semester semester;

    /**
     * Many-to-one relationship with the Day entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "day_id", referencedColumnName = "id", nullable = false)
    private Day day;

    /**
     * Many-to-one relationship with the Period entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "period_id", referencedColumnName = "id", nullable = false)
    private Period period;

    /**
     * Many-to-one relationship with the Course entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
    private Course course;
}
    