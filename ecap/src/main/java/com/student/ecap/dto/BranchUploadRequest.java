package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for uploading branch data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BranchUploadRequest {
    // branch_id is auto-generated, so it's not included in the request
    private String branchName;
}
