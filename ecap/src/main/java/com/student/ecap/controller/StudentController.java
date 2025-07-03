package com.student.ecap.controller;

import com.student.ecap.dto.LoginRequest;
import com.student.ecap.dto.LoginResponse;
import com.student.ecap.dto.StudentDetailsResponse;
import com.student.ecap.dto.StudentSemesterMarkResponse; // New import
import com.student.ecap.dto.StudentUpdateRequest;
import com.student.ecap.service.AuthService;
import com.student.ecap.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List; // New import for List
import java.util.Optional;

/**
 * REST Controller for student-related operations, including login,
 * updating personal details, checking details, and retrieving semester marks.
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = {"http://localhost:5173", "http://20.244.28.21:5173"})
public class StudentController {

    private final AuthService authService;
    private final StudentService studentService;

    @Autowired
    public StudentController(AuthService authService, StudentService studentService) {
        this.authService = authService;
        this.studentService = studentService;
    }

    /**
     * Endpoint for student login.
     *
     * @param loginRequest DTO containing roll number and password.
     * @return ResponseEntity with LoginResponse and HTTP status.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> studentLogin(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.authenticateStudent(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    /**
     * Endpoint for a student to update their own details.
     *
     * @param rollNumber The roll number of the student to update.
     * @param updateRequest DTO with fields to update.
     * @return ResponseEntity with updated StudentDetailsResponse or error.
     */
    @PutMapping("/{rollNumber}/profile")
    public ResponseEntity<StudentDetailsResponse> updateStudentDetails(
            @PathVariable String rollNumber,
            @RequestBody StudentUpdateRequest updateRequest) {

        Optional<StudentDetailsResponse> updatedStudent = studentService.updateStudentDetails(rollNumber, updateRequest);

        return updatedStudent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint for a student to check their own details.
     *
     * @param rollNumber The roll number of the student to retrieve.
     * @return ResponseEntity with StudentDetailsResponse or error.
     */
    @GetMapping("/{rollNumber}/profile")
    public ResponseEntity<StudentDetailsResponse> getStudentDetails(@PathVariable String rollNumber) {
        Optional<StudentDetailsResponse> studentDetails = studentService.getStudentDetails(rollNumber);

        return studentDetails.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to get all semester marks for a specific student and semester.
     *
     * @param rollNumber The roll number of the student.
     * @param semesterId The ID of the semester.
     * @return ResponseEntity containing a list of StudentSemesterMarkResponse DTOs.
     */
    @GetMapping("/{rollNumber}/semester/{semesterId}/marks")
    public ResponseEntity<List<StudentSemesterMarkResponse>> getSemesterMarks(
            @PathVariable String rollNumber,
            @PathVariable Integer semesterId) {

        List<StudentSemesterMarkResponse> marks = studentService.getSemesterMarksForStudent(rollNumber, semesterId);

        if (marks.isEmpty()) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content if no marks found
        }
        return ResponseEntity.ok(marks); // HTTP 200 OK with the list of marks
    }
}
