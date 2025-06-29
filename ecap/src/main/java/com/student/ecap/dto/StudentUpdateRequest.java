package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for updating student details.
 * Contains only fields that a student is allowed to update.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUpdateRequest {
    // Assuming student can update address, email, mobileno
    private String address;
    private String email;
    private String mobileno;
    private String bloodgroup; // Assuming blood group can also be update
}
