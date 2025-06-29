package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for uploading semester exam marks data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemesterMarksUploadRequest {
    private String rollNumber;
    private Integer courseId;
    private Integer semesterId;
    private Double marksObtained;
    private String grade; // This should match a valid grade in the Grade table
}
