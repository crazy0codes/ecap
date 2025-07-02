package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for uploading course data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseUploadRequest {
    // No course_id as it's auto-generated
    private String courseCode;
    private String courseName;
    private Double credits;
}
