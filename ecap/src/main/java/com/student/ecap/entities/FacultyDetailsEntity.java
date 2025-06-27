package com.student.ecap.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "faculty_details")
public class FacultyDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "Faculty ID is required")
    private String facultyId;

    @ElementCollection
    @CollectionTable(name = "faculty_courses", joinColumns = @JoinColumn(name = "faculty_id"))
    @Column(name = "course")
    private List<String> coursesTaught;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth;

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<String> coursesTaught) {
        this.coursesTaught = coursesTaught;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
