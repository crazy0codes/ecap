package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'course' table in the database.
 * Stores information about academic courses.
 */
@Entity
@Table(name = "course")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    /**
     * The unique identifier for the course.
     * Mapped to the 'course_id' column, which is an auto-incrementing primary key.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "course_id") // Maps to the column 'course_id'
    private Integer courseId;

    /**
     * The code for the course (e.g., CS101).
     * Mapped to the 'course_code' column.
     */
    @Column(name = "course_code", length = 255) // Maps to the column 'course_code'
    private String courseCode;

    /**
     * The name of the course.
     * Mapped to the 'course_name' column.
     */
    @Column(name = "course_name", length = 255) // Maps to the column 'course_name'
    private String courseName;

    /**
     * The credit value for the course.
     * Mapped to the 'credits' column. Using Double for decimal.
     */
    @Column(name = "credits") // Maps to the column 'credits'
    private Double credits;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Double getCredits() {
        return credits;
    }

    public void setCredits(Double credits) {
        this.credits = credits;
    }
}
