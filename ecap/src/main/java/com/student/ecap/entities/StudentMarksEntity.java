package com.student.ecap.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "student_marks")
public class StudentMarksEntity {

    @Id
    private String id;

    private String rollno;
    private int semNo;

    private int subject1Marks;
    private int subject2Marks;
    private int subject3Marks;
    private int subject4Marks;
    private int subject5Marks;

    public StudentMarksEntity() {}

    public StudentMarksEntity(String rollno, int semNo, int subject1Marks, int subject2Marks,
                              int subject3Marks, int subject4Marks, int subject5Marks) {
        this.rollno = rollno;
        this.semNo = semNo;
        this.subject1Marks = subject1Marks;
        this.subject2Marks = subject2Marks;
        this.subject3Marks = subject3Marks;
        this.subject4Marks = subject4Marks;
        this.subject5Marks = subject5Marks;
    }

    // Getters and setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRollno() { return rollno; }
    public void setRollno(String rollno) { this.rollno = rollno; }

    public int getSemNo() { return semNo; }
    public void setSemNo(int semNo) { this.semNo = semNo; }

    public int getSubject1Marks() { return subject1Marks; }
    public void setSubject1Marks(int subject1Marks) { this.subject1Marks = subject1Marks; }

    public int getSubject2Marks() { return subject2Marks; }
    public void setSubject2Marks(int subject2Marks) { this.subject2Marks = subject2Marks; }

    public int getSubject3Marks() { return subject3Marks; }
    public void setSubject3Marks(int subject3Marks) { this.subject3Marks = subject3Marks; }

    public int getSubject4Marks() { return subject4Marks; }
    public void setSubject4Marks(int subject4Marks) { this.subject4Marks = subject4Marks; }

    public int getSubject5Marks() { return subject5Marks; }
    public void setSubject5Marks(int subject5Marks) { this.subject5Marks = subject5Marks; }
}
