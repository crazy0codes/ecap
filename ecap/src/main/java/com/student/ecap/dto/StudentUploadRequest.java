package com.student.ecap.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO for uploading student data.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentUploadRequest {
    private String rollNumber;
    private String name;
    private String regulationId;
    private Integer branchId;
    private String address;
    private String email;
    private String mobileno;
    private String bloodgroup;
    private String fatherName;
    private String motherName;
    private String password; // For the associated User record
}
