package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO for student login responses, now including JWT and user roles.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private boolean success;
    private String rollNumber;
    private String jwt; // New field for the JWT token
    private List<String> roles; // New field for user roles
}
