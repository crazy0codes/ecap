package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents the 'user' table in the database.
 * Stores user authentication details and now includes roles for authorization.
 */
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "roll_number")
}) // Ensure roll_number is unique
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * The roll number of the user, which acts as both primary key for 'user'
     * and a foreign key referencing 'student.roll_number'.
     */
    @Id // Marks this field as the primary key
    @Column(name = "roll_number", length = 255) // Maps to the column 'roll_number'
    private String rollNumber;

    /**
     * The password for the user.
     * Mapped to the 'password' column. This will be a HASHED password.
     */
    @Column(name = "password", length = 255)
    private String password;

    /**
     * One-to-one relationship with the Student entity.
     * This establishes that each user record corresponds to exactly one student.
     * @MapsId indicates that the primary key of this entity ('rollNumber')
     * is also a foreign key to the associated 'Student' entity.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Indicates that the PK of this entity is also a FK to the associated entity
    @JoinColumn(name = "roll_number", referencedColumnName = "roll_number")
    private Student student;

    /**
     * Many-to-many relationship with Role entity.
     * Users can have multiple roles.
     * This is the owning side of the relationship.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", // Name of the join table
            joinColumns = @JoinColumn(name = "user_roll_number"), // Column in join table referring to User
            inverseJoinColumns = @JoinColumn(name = "role_id")) // Column in join table referring to Role
    private Set<Role> roles = new HashSet<>();

    // Constructor for creating a new User with roles
    public User(String rollNumber, String password, Set<Role> roles) {
        this.rollNumber = rollNumber;
        this.password = password;
        this.roles = roles;
    }
}
