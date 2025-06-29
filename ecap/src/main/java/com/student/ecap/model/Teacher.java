package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'Teacher' table in the database.
 * Stores information about teachers, including their branch and assigned course.
 */
@Entity
@Table(name = "teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {

    /**
     * The unique identifier for the teacher, which is the primary key.
     * Mapped to the 'id' column.
     */
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configures auto-increment for the ID
    @Column(name = "id") // Maps to the column 'id'
    private Integer id;

    /**
     * The branch the teacher belongs to.
     * Many-to-one relationship with the Branch entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch", referencedColumnName = "branch_id") // Defines the foreign key column
    private Branch branch;

    /**
     * The teacher's first name.
     * Mapped to the 'fistName' column.
     */
    @Column(name = "fistName", length = 255) // Maps to the column 'fistName'
    private String firstName;

    /**
     * The teacher's last name.
     * Mapped to the 'lastName' column.
     */
    @Column(name = "lastName", length = 255) // Maps to the column 'lastName'
    private String lastName;

    /**
     * The teacher's password.
     * Mapped to the 'password' column.
     */
    @Column(name = "password", length = 255) // Maps to the column 'password'
    private String password;

    /**
     * The course the teacher is associated with.
     * Many-to-one relationship with the Course entity.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course", referencedColumnName = "course_id") // Defines the foreign key column
    private Course course;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
