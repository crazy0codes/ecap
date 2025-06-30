package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the 'role' table in the database.
 * Defines the different user roles (e.g., ADMIN, FACULTY, STUDENT).
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

    @Enumerated(EnumType.STRING) // Store enum name as a String in DB
    @Column(length = 20, unique = true, nullable = false)
    private ERole name; // Using an enum for role names

    // Many-to-many relationship with User
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public Role(ERole name) {
        this.name = name;
    }
}
