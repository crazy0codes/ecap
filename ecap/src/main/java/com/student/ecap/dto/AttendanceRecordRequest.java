package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for marking attendance for a single student within a session.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordRequest {
    private String studentRollNumber;
    private String status; // e.g., "Present", "Absent"
}
