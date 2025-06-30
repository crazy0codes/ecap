package com.student.ecap.controller;

import com.student.ecap.dto.LoginRequest; // Keep this, but it's handled by AuthController now
import com.student.ecap.dto.LoginResponse; // Keep this, but it's handled by AuthController now
import com.student.ecap.dto.StudentDetailsResponse;
import com.student.ecap.dto.StudentSemesterMarkResponse;
import com.student.ecap.dto.StudentUpdateRequest;
import com.student.ecap.service.AuthService; // Keep this for now, though login moves to AuthController
import com.student.ecap.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // NEW: Import PreAuthorize
import org.springframework.security.core.Authentication; // NEW: For getting current user details
import org.springframework.security.core.context.SecurityContextHolder; // NEW: For getting current user details
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for student-related operations, including login,
 * updating personal details, checking details, and retrieving semester marks.
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    // AuthService is primarily used by AuthController now, but kept here if other auth-related
    // student services were to be added (though better in AuthService itself)
    private final AuthService authService;
    private final StudentService studentService;

    @Autowired
    public StudentController(AuthService authService, StudentService studentService) {
        this.authService = authService;
        this.studentService = studentService;
    }

    // This login endpoint will be moved to AuthController for a cleaner separation
    // @PostMapping("/login")
    // public ResponseEntity<LoginResponse> studentLogin(@RequestBody LoginRequest loginRequest) {
    //     LoginResponse response = authService.authenticateStudent(loginRequest);
    //     if (response.isSuccess()) {
    //         return ResponseEntity.ok(response);
    //     } else {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    //     }
    // }

    /**
     * Endpoint for a student to update their own details.
     * Only a STUDENT can update their own profile. Admin can update any student's profile.
     * @PreAuthorize checks if the authenticated user has ROLE_STUDENT OR ROLE_ADMIN,
     * AND if they are trying to update their own profile (for students).
     *
     * @param rollNumber The roll number of the student to update.
     * @param updateRequest DTO with fields to update.
     * @return ResponseEntity with updated StudentDetailsResponse or error.
     */
    @PutMapping("/{rollNumber}/profile")
    @PreAuthorize("hasRole('STUDENT') and #rollNumber == authentication.principal.username or hasRole('ADMIN')")
    public ResponseEntity<StudentDetailsResponse> updateStudentDetails(
            @PathVariable String rollNumber,
            @RequestBody StudentUpdateRequest updateRequest) {

        // The @PreAuthorize above handles the logic. If execution reaches here,
        // it means the user is authorized.
        Optional<StudentDetailsResponse> updatedStudent = studentService.updateStudentDetails(rollNumber, updateRequest);

        return updatedStudent.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint for a student to check their own details.
     * Only a STUDENT can view their own profile. Faculty/Admin can view any student's profile.
     *
     * @param rollNumber The roll number of the student to retrieve.
     * @return ResponseEntity with StudentDetailsResponse or error.
     */
    @GetMapping("/{rollNumber}/profile")
    @PreAuthorize("hasRole('STUDENT') and #rollNumber == authentication.principal.username or hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<StudentDetailsResponse> getStudentDetails(@PathVariable String rollNumber) {
        Optional<StudentDetailsResponse> studentDetails = studentService.getStudentDetails(rollNumber);

        return studentDetails.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to get all semester marks for a specific student and semester.
     * Only a STUDENT can view their own marks. Faculty/Admin can view any student's marks.
     *
     * @param rollNumber The roll number of the student.
     * @param semesterId The ID of the semester.
     * @return ResponseEntity containing a list of StudentSemesterMarkResponse DTOs.
     */
    @GetMapping("/{rollNumber}/semester/{semesterId}/marks")
    @PreAuthorize("hasRole('STUDENT') and #rollNumber == authentication.principal.username or hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<List<StudentSemesterMarkResponse>> getSemesterMarks(
            @PathVariable String rollNumber,
            @PathVariable Integer semesterId) {

        List<StudentSemesterMarkResponse> marks = studentService.getSemesterMarksForStudent(rollNumber, semesterId);

        if (marks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(marks);
    }
}
