package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for uploading a single timetable entry.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimetableUploadRequest {
    private Integer branchId;
    private Integer semesterId;
    private Integer dayId; // ID of the Day entity (e.g., 1 for Monday)
    private Integer periodId; // ID of the Period entity (e.g., 1 for Period 1)
    private Integer courseId;
}
    