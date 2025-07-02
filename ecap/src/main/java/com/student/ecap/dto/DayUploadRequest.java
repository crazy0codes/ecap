package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for uploading day names (e.g., "Monday").
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DayUploadRequest {
    private String name;
}
    