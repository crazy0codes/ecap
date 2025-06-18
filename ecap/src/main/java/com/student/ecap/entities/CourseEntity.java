package com.student.ecap.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class CourseEntity {

    @Id
    private String id;
    private String courseName;
    private String description;
    private int duration;

    public CourseEntity() {}

    public CourseEntity(String courseName, String description, int duration) {
        this.courseName = courseName;
        this.description = description;
        this.duration = duration;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
