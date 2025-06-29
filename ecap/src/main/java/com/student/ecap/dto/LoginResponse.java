package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for student login responses.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private boolean success;
    private String rollNumber; // Optionally return the roll number of the logged-in student
    // In a real application, this might also contain a JWT token.
}
