package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'period' table in the database.
 * Stores the period numbers for a day in the timetable.
 */
@Entity
@Table(name = "period")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Period {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "period_number", unique = true, nullable = false)
    private Integer periodNumber; // e.g., 1, 2, 3

    public Period(Integer periodNumber) {
        this.periodNumber = periodNumber;
    }
}
    