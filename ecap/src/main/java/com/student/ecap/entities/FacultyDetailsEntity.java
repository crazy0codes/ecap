package com.student.ecap.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "FacultyDetails")
public class FacultyDetailsEntity {

    @Id
    private String id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Faculty ID is required")
    private String facultyId;
    @JsonProperty("coursesTeach")
    private List<@NotBlank(message = "Course name cannot be blank") String> coursesTaught;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "Date of birth is required")
    private String dateOfBirth; // You can also use `LocalDate` if preferred

    // Constructors
    public FacultyDetailsEntity() {
    }

    public FacultyDetailsEntity(String name, String facultyId, List<String> coursesTaught, String branch, String dateOfBirth) {
        this.name = name;
        this.facultyId = facultyId;
        this.coursesTaught = coursesTaught;
        this.branch = branch;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public List<String> getCoursesTaught() {
        return coursesTaught;
    }

    public void setCoursesTaught(List<String> coursesTaught) {
        this.coursesTaught = coursesTaught;
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
