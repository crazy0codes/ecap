package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Represents the 'semester_marks' table in the database.
 * Stores final semester marks and grades obtained by students for specific courses.
 * Uses a composite primary key managed by @EmbeddedId.
 */
@Entity
@Table(name = "semester_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Removed @IdClass, now using @EmbeddedId
public class SemesterMarks {

    @EmbeddedId // Indicates that the primary key is an embedded object
    private SemesterMarksId id;

    /**
     * The student related to these marks.
     * @MapsId links the 'rollNumber' field in SemesterMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rollNumber") // This tells JPA that the 'rollNumber' field within the 'id' (SemesterMarksId) comes from this association
    @JoinColumn(name = "roll_number", referencedColumnName = "roll_number")
    private Student student;

    /**
     * The course for which marks are recorded.
     * @MapsId links the 'courseId' field in SemesterMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId") // Link to the 'courseId' field within the embedded ID
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    /**
     * The semester in which the marks were obtained.
     * @MapsId links the 'semesterId' field in SemesterMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("semesterId") // Link to the 'semesterId' field within the embedded ID
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id")
    private Semester semester;

    /**
     * The marks obtained by the student in the semester.
     * Mapped to the 'marks_obtained' column. Using Double for decimal.
     */
    @Column(name = "marks_obtained")
    private Double marksObtained;

    /**
     * The grade obtained by the student for the course in the semester.
     * Many-to-one relationship with the Grade entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grade", referencedColumnName = "grade")
    private Grade grade;

    /**
     * Embeddable class representing the composite primary key for SemesterMarks.
     * Contains primitive types that correspond to the foreign key columns.
     */
    @Embeddable // Marks this class as embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SemesterMarksId implements Serializable {
        // These fields must match the 'name' attribute of the @JoinColumn
        // or be mapped by @MapsId in the parent entity.
        // They represent the primitive values of the foreign keys.
        @Column(name = "roll_number")
        private String rollNumber;

        @Column(name = "course_id")
        private Integer courseId;

        @Column(name = "semester_id")
        private Integer semesterId;
    }
}
