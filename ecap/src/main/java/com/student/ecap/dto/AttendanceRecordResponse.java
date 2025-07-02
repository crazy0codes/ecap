package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for returning details of an individual attendance record.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordResponse {
    private Integer recordId;
    private String studentRollNumber;
    private String studentName;
    private String status; // "Present" or "Absent"
}
