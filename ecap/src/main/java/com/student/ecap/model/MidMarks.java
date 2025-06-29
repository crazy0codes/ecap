package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Represents the 'mid_marks' table in the database.
 * Stores mid-exam marks obtained by students for specific courses in a given semester.
 * Uses a composite primary key managed by @EmbeddedId.
 */
@Entity
@Table(name = "mid_marks")
@Data
@NoArgsConstructor
@AllArgsConstructor
// Removed @IdClass, now using @EmbeddedId
public class MidMarks {

    @EmbeddedId // Indicates that the primary key is an embedded object
    private MidMarksId id;

    /**
     * The student related to these marks.
     * @MapsId links the 'rollNumber' field in MidMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("rollNumber") // This tells JPA that the 'rollNumber' field within the 'id' (MidMarksId) comes from this association
    @JoinColumn(name = "roll_number", referencedColumnName = "roll_number")
    private Student student;

    /**
     * The course for which marks are recorded.
     * @MapsId links the 'courseId' field in MidMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("courseId") // Link to the 'courseId' field within the embedded ID
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    private Course course;

    /**
     * The semester in which the marks were obtained.
     * @MapsId links the 'semesterId' field in MidMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("semesterId") // Link to the 'semesterId' field within the embedded ID
    @JoinColumn(name = "semester_id", referencedColumnName = "semester_id")
    private Semester semester;

    /**
     * The specific mid-exam (Mid 1 or Mid 2).
     * @MapsId links the 'midId' field in MidMarksId to this association's primary key.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("midId") // Link to the 'midId' field within the embedded ID
    @JoinColumn(name = "mid_id", referencedColumnName = "mid_id")
    private MidExam midExam;

    /**
     * The marks obtained by the student in the mid-exam.
     * Mapped to the 'marks_obtained' column. Using Double for decimal.
     */
    @Column(name = "marks_obtained")
    private Double marksObtained;

    /**
     * Embeddable class representing the composite primary key for MidMarks.
     * Contains primitive types that correspond to the foreign key columns.
     */
    @Embeddable // Marks this class as embeddable, meaning it can be used as part of another entity's primary key
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MidMarksId implements Serializable {
        // These fields must match the 'name' attribute of the @JoinColumn
        // or be mapped by @MapsId in the parent entity.
        // They represent the primitive values of the foreign keys.
        @Column(name = "roll_number")
        private String rollNumber;

        @Column(name = "course_id")
        private Integer courseId;

        @Column(name = "semester_id")
        private Integer semesterId;

        @Column(name = "mid_id")
        private Integer midId;
    }
}
