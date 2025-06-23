package com.student.ecap.entities;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "Marks")
public class StudentMarksEntity {

    @Id
    private String id;
    @NotBlank
    private String rollno;
    @NotBlank
    private int semNo;

    private Map<String, Integer> marks;
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }

    public int getSemNo() { return semNo; }
    public void setSemNo(int semNo) { this.semNo = semNo; }

    public Map<String, Integer> getMarks() { return marks; }
    public void setMarks(Map<String, Integer> marks) { this.marks = marks; }
}
