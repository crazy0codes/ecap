package com.student.ecap.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Semester")
public class SemesterEntity {

    @Id
    private String id;

    @NotNull(message = "Semester number is required")
    @Min(value = 1, message = "Semester number must be at least 1")
    private Integer semNo;

    @NotBlank(message = "Branch is required")
    private String branch;

    @NotBlank(message = "Subject 1 is required")
    private String subject1;

    @NotBlank(message = "Subject 2 is required")
    private String subject2;

    @NotBlank(message = "Subject 3 is required")
    private String subject3;

    @NotBlank(message = "Subject 4 is required")
    private String subject4;

    @NotBlank(message = "Subject 5 is required")
    private String subject5;

    // Constructors
    public SemesterEntity() {}

    public SemesterEntity(Integer semNo, String subject1, String subject2, String subject3, String subject4, String subject5) {
        this.semNo = semNo;
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.subject4 = subject4;
        this.subject5 = subject5;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSemNo() {
        return semNo;
    }

    public void setSemNo(Integer semNo) {
        this.semNo = semNo;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getSubject1() {
        return subject1;
    }

    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public String getSubject4() {
        return subject4;
    }

    public void setSubject4(String subject4) {
        this.subject4 = subject4;
    }

    public String getSubject5() {
        return subject5;
    }

    public void setSubject5(String subject5) {
        this.subject5 = subject5;
    }
}
