package com.student.ecap.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Semester")
public class SemesterEntity {


    private String id;

    private int semNo;
    private String subject1;
    private String subject2;
    private String subject3;
    private String subject4;
    private String subject5;

    // Constructors
    public SemesterEntity() {}

    public SemesterEntity(int semNo, String subject1, String subject2, String subject3, String subject4, String subject5) {
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

    public int getSemNo() {
        return semNo;
    }

    public void setSemNo(int semNo) {
        this.semNo = semNo;
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