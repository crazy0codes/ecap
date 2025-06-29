package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'user' table in the database.
 * Stores user authentication details, specifically linking to a student's roll number.
 * The primary key 'roll_number' here is also a foreign key to the 'student' table.
 */
@Entity
@Table(name = "user")
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
     * Mapped to the 'password' column.
     */
    @Column(name = "password", length = 255) // Maps to the column 'password'
    private String password;

    /**
     * One-to-one relationship with the Student entity.
     * This establishes that each user record corresponds to exactly one student.
     * @MapsId indicates that the primary key of this entity ('rollNumber')
     * is also a foreign key to the associated 'Student' entity.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId // Indicates that the PK of this entity is also a FK to the associated entity
    @JoinColumn(name = "roll_number", referencedColumnName = "roll_number") // Explicitly define the join column
    private Student student;

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
