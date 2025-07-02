package com.student.ecap.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for uploading period numbers (e.g., 1, 2).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodUploadRequest {
    private Integer periodNumber;
}
    