package com.student.ecap.controller;

import com.student.ecap.dto.AttendanceRecordRequest;
import com.student.ecap.dto.AttendanceRecordResponse;
import com.student.ecap.dto.AttendanceSessionCreateRequest;
import com.student.ecap.dto.AttendanceSessionResponse;
import com.student.ecap.dto.StudentAttendanceSummaryResponse;
import com.student.ecap.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing attendance sessions and records.
 * All endpoints are publicly accessible as authentication is removed.
 */
@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = {"http://localhost:5173", "http://20.244.28.21:5173"})
public class AttendanceController {

    private final AttendanceService attendanceService;

    @Autowired
    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    /**
     * Creates a new attendance session.
     * A teacher would typically use this to start recording attendance for a class.
     * @param request DTO containing details for the new session.
     * @return ResponseEntity with the created session details or an error.
     */
    @PostMapping("/sessions")
    public ResponseEntity<AttendanceSessionResponse> createAttendanceSession(@RequestBody AttendanceSessionCreateRequest request) {
        try {
            Optional<AttendanceSessionResponse> response = attendanceService.createAttendanceSession(request);
            return response.map(session -> ResponseEntity.status(HttpStatus.CREATED).body(session))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build()); // Should not happen with current service logic, but good for robustness
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Invalid FKs
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Marks attendance for multiple students in a specific session.
     * @param sessionId The ID of the attendance session.
     * @param attendanceRecords List of AttendanceRecordRequest DTOs (studentRollNumber, status).
     * @return ResponseEntity with the created/updated attendance records or an error.
     */
    @PostMapping("/sessions/{sessionId}/records")
    public ResponseEntity<List<AttendanceRecordResponse>> markAttendance(
            @PathVariable Integer sessionId,
            @RequestBody List<AttendanceRecordRequest> attendanceRecords) {
        try {
            List<AttendanceRecordResponse> createdRecords = attendanceService.markAttendance(sessionId, attendanceRecords);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRecords);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Session or student not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Retrieves all attendance records for a specific session.
     * @param sessionId The ID of the attendance session.
     * @return ResponseEntity with a list of attendance records.
     */
    @GetMapping("/sessions/{sessionId}/records")
    public ResponseEntity<List<AttendanceRecordResponse>> getAttendanceRecordsBySession(@PathVariable Integer sessionId) {
        List<AttendanceRecordResponse> records = attendanceService.getAttendanceRecordsBySession(sessionId);
        if (records.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(records);
    }

    /**
     * Retrieves a summary of attendance for a specific student.
     * @param studentRollNumber The roll number of the student.
     * @return ResponseEntity with a list of attendance summaries for the student.
     */
    @GetMapping("/students/{studentRollNumber}/summary")
    public ResponseEntity<List<StudentAttendanceSummaryResponse>> getStudentAttendanceSummary(@PathVariable String studentRollNumber) {
        List<StudentAttendanceSummaryResponse> summary = attendanceService.getStudentAttendanceSummary(studentRollNumber);
        if (summary.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(summary);
    }

    /**
     * Retrieves all attendance sessions for a specific branch, semester, and date.
     * This can be used by a teacher to see all classes they need to take attendance for on a given day.
     * @param branchId ID of the branch.
     * @param semesterId ID of the semester.
     * @param date The actual calendar date (format YYYY-MM-DD).
     * @return ResponseEntity with a list of attendance sessions.
     */
    @GetMapping("/sessions/branch/{branchId}/semester/{semesterId}/date/{date}")
    public ResponseEntity<List<AttendanceSessionResponse>> getAttendanceSessionsByDate(
            @PathVariable Integer branchId,
            @PathVariable Integer semesterId,
            @PathVariable LocalDate date) { // Spring automatically converts String to LocalDate

        List<AttendanceSessionResponse> sessions = attendanceService.getAttendanceSessionsByDate(branchId, semesterId, date);
        if (sessions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sessions);
    }
}
