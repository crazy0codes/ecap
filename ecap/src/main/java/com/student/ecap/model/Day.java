package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'day' table in the database.
 * Stores the days of the week for timetable purposes.
 */
@Entity
@Table(name = "day")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Day {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 50, unique = true, nullable = false)
    private String name; // e.g., "Monday", "Tuesday"

    public Day(String name) {
        this.name = name;
    }
}
    