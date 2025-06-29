package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'semester' table in the database.
 * Stores information about academic semesters (e.g., Semester 1, Semester 2).
 */
@Entity
@Table(name = "semester")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester {

    /**
     * The unique identifier for the semester.
     * Mapped to the 'semester_id' column, which is an auto-incrementing primary key.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "semester_id") // Maps to the column 'semester_id'
    private Integer semesterId;

    /**
     * The number of the semester (e.g., 1 to 8).
     * Mapped to the 'semester_number' column.
     */
    @Column(name = "semester_number") // Maps to the column 'semester_number'
    private Integer semesterNumber;

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(Integer semesterNumber) {
        this.semesterNumber = semesterNumber;
    }
}