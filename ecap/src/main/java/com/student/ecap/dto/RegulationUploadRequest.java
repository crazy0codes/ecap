package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for uploading regulation data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegulationUploadRequest {
    private String regulationId; // Regulation ID is specified, not auto-generated
}
