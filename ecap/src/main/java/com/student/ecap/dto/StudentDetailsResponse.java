package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for sending student details in a response.
 * Avoids exposing all entity fields directly.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDetailsResponse {
    private String rollNumber;
    private String name;
    private String regulationId; // Just the ID for simplicity in DTO
    private Integer branchId;    // Just the ID for simplicity in DTO
    private String address;
    private String email;
    private String mobileno;
    private String bloodgroup;
    private String fatherName;
    private String motherName;
}
