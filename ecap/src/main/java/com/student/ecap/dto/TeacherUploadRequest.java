package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for uploading teacher data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherUploadRequest {
    // No id as it's auto-generated
    private Integer branchId; // ID of the branch the teacher belongs to
    private String firstName;
    private String lastName;
    private String password;
    private Integer courseId; // ID of the course the teacher is associated with
}
