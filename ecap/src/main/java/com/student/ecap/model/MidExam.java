package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'mid_exam' table in the database.
 * Stores information about mid-term exams (e.g., Mid 1, Mid 2).
 */
@Entity
@Table(name = "mid_exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MidExam {

    /**
     * The unique identifier for the mid-exam.
     * Mapped to the 'mid_id' column, which is an auto-incrementing primary key.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "mid_id") // Maps to the column 'mid_id'
    private Integer midId;

    /**
     * The number of the mid-exam (e.g., 1 or 2).
     * Mapped to the 'mid_number' column.
     */
    @Column(name = "mid_number") // Maps to the column 'mid_number'
    private Integer midNumber;

    public Integer getMidId() {
        return midId;
    }

    public void setMidId(Integer midId) {
        this.midId = midId;
    }

    public Integer getMidNumber() {
        return midNumber;
    }

    public void setMidNumber(Integer midNumber) {
        this.midNumber = midNumber;
    }
}
