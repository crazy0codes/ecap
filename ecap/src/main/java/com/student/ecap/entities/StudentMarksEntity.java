package com.student.ecap.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Marks")
public class StudentMarksEntity {

    @Id
    private String id;

    @NotBlank(message = "Roll number is required")
    private String rollno;

    @NotNull(message = "Semester number is required")
    @Min(value = 1, message = "Semester number must be at least 1")
    private Integer semNo;

    @NotEmpty(message = "Marks cannot be empty")
    private Map<String, @NotNull(message = "Mark must not be null") Integer> marks;

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }

    public Integer getSemNo() { return semNo; }
    public void setSemNo(Integer semNo) { this.semNo = semNo; }

    public Map<String, Integer> getMarks() { return marks; }
    public void setMarks(Map<String, Integer> marks) { this.marks = marks; }
}
