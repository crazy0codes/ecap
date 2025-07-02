package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning timetable details to the client.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimetableResponse {
    private Integer id; // Timetable entry ID
    private String branchName;
    private Integer semesterNumber;
    private String dayName;
    private Integer periodNumber;
    private String courseCode;
    private String courseName;
}
    