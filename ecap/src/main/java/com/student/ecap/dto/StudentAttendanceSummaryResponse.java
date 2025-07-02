package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for summarizing a student's attendance for a specific class session.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttendanceSummaryResponse {
    private Integer sessionId;
    private String courseName;
    private String teacherName; // e.g., "John Doe"
    private LocalDate sessionDate;
    private Integer periodNumber;
    private String dayName;
    private String attendanceStatus; // "Present" or "Absent"
}
