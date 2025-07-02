package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for returning details of an attendance session.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceSessionResponse {
    private Integer sessionId;
    private String teacherName; // Changed from teacherFirstName and teacherLastName
    private String courseName;
    private String branchName;
    private Integer semesterNumber;
    private Integer periodNumber;
    private String dayName;
    private LocalDate date;
}
