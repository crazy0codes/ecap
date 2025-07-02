package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for representing a single semester mark entry for a student,
 * including relevant course details.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentSemesterMarkResponse {
    private String courseCode;
    private String courseName;
    private Double credits;
    private Double marksObtained;
    private String grade;
    private Integer semesterNumber; // To confirm the semester
}
