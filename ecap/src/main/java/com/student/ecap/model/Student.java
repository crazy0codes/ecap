package com.student.ecap.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the 'student' table in the database.
 * Stores personal and academic information about a student.
 */
@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    /**
     * The unique roll number of the student, which is the primary key.
     * Mapped to the 'roll_number' column.
     */
    @Id // Marks this field as the primary key
    @Column(name = "roll_number", length = 255) // Maps to the column 'roll_number'
    private String rollNumber;

    /**
     * The full name of the student.
     * Mapped to the 'name' column.
     */
    @Column(name = "name", length = 255) // Maps to the column 'name'
    private String name;

    /**
     * The regulation under which the student is enrolled.
     * Many-to-one relationship with the Regulation entity.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Many students can be under one regulation
    @JoinColumn(name = "regulation_id", referencedColumnName = "regulation_id") // Defines the foreign key column
    private Regulation regulation;

    /**
     * The branch the student belongs to.
     * Many-to-one relationship with the Branch entity.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Many students can belong to one branch
    @JoinColumn(name = "branch_id", referencedColumnName = "branch_id") // Defines the foreign key column
    private Branch branch;

    /**
     * The student's address.
     * Mapped to the 'address' column.
     */
    @Column(name = "address", length = 255) // Maps to the column 'address'
    private String address;

    /**
     * The student's email address.
     * Mapped to the 'email' column.
     */
    @Column(name = "email", length = 255) // Maps to the column 'email'
    private String email;

    /**
     * The student's mobile number.
     * Mapped to the 'mobileno' column.
     */
    @Column(name = "mobileno", length = 255) // Maps to the column 'mobileno'
    private String mobileno;

    /**
     * The student's blood group.
     * Mapped to the 'bloodgroup' column.
     */
    @Column(name = "bloodgroup", length = 255) // Maps to the column 'bloodgroup'
    private String bloodgroup;

    /**
     * The student's father's name.
     * Mapped to the 'fathername' column.
     */
    @Column(name = "fathername", length = 255) // Maps to the column 'fathername'
    private String fatherName;

    /**
     * The student's mother's name.
     * Mapped to the 'mothername' column.
     */
    @Column(name = "mothername", length = 255) // Maps to the column 'mothername'
    private String motherName;

    // One-to-one relationship with User (Student's roll_number is also user's roll_number/PK)
    // This side is the owning side if User's PK is also FK to Student's PK.
    // For a simple one-to-one, we can omit mappedBy and use @MapsId if user.roll_number directly uses student's PK.
    // If user.roll_number is just a FK, then @OneToOne and @JoinColumn is more direct.
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Regulation getRegulation() {
        return regulation;
    }

    public void setRegulation(Regulation regulation) {
        this.regulation = regulation;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
