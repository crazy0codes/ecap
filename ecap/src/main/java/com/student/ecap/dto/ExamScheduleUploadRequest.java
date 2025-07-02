package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

/**
 * DTO for uploading exam schedule and events data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamScheduleUploadRequest {
    private Integer semesterId;
    private String regulationId;
    private LocalDate startDate; // For the related ExamEvent
    private LocalDate endDate;   // For the related ExamEvent
    private String description;  // For the related ExamEvent (e.g., "Mid 1")
}
