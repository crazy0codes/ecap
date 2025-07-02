package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for bulk uploading attendance records via UploadService.
 * Note: For real-world use, marking attendance via AttendanceController is preferred.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceRecordUploadRequest {
    private Integer attendanceSessionId;
    private String studentRollNumber;
    private String status; // "Present" or "Absent"
}
