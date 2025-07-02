package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'role' table in the database.
 * Stores distinct user roles (e.g., STUDENT, FACULTY, ADMIN).
 */
@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING) // Stores the enum name as a String in the DB
    @Column(length = 20, unique = true, nullable = false)
    private ERole name; // e.g., ROLE_STUDENT, ROLE_FACULTY, ROLE_ADMIN

    public Role(ERole name) {
        this.name = name;
    }
}
