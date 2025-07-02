package com.student.ecap.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'Grade' table in the database.
 * Stores possible grade values (e.g., A+, A, B, etc.).
 */
@Entity
@Table(name = "grade")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Grade {

    /**
     * The grade value, which is the primary key.
     * Mapped to the 'grade' column.
     */
    @Id // Marks this field as the primary key
    @Column(name = "grade", length = 255) // Maps to the column 'grade'
    private String grade;

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}

