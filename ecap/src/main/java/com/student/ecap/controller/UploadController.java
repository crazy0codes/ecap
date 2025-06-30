package com.student.ecap.controller;

import com.student.ecap.dto.*;
import com.student.ecap.model.*;
import com.student.ecap.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize; // NEW: Import PreAuthorize
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for uploading various types of data into the system.
 * These endpoints are typically secured and accessible only to administrators.
 */
@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:5173")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * Endpoint to upload a list of branches.
     * Only ADMIN can perform this operation.
     *
     * @param branchRequests List of BranchUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/branches")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadBranches(@RequestBody List<BranchUploadRequest> branchRequests) {
        try {
            List<Branch> uploadedBranches = uploadService.uploadBranches(branchRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedBranches.size() + " branches.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading branches: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of courses.
     * Only ADMIN can perform this operation.
     *
     * @param courseRequests List of CourseUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/courses")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadCourses(@RequestBody List<CourseUploadRequest> courseRequests) {
        try {
            List<Course> uploadedCourses = uploadService.uploadCourses(courseRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedCourses.size() + " courses.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading courses: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of semester exam marks.
     * Only FACULTY or ADMIN can perform this operation.
     *
     * @param semesterMarksRequests List of SemesterMarksUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/semester-marks")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<String> uploadSemesterMarks(@RequestBody List<SemesterMarksUploadRequest> semesterMarksRequests) {
        try {
            List<SemesterMarks> uploadedMarks = uploadService.uploadSemesterMarks(semesterMarksRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedMarks.size() + " semester marks.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error uploading semester marks: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading semester marks: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of regulations.
     * Only ADMIN can perform this operation.
     *
     * @param regulationRequests List of RegulationUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/regulations")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadRegulations(@RequestBody List<RegulationUploadRequest> regulationRequests) {
        try {
            List<Regulation> uploadedRegulations = uploadService.uploadRegulations(regulationRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedRegulations.size() + " regulations.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading regulations: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of teacher details.
     * Only ADMIN can perform this operation.
     *
     * @param teacherRequests List of TeacherUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/teachers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadTeachers(@RequestBody List<TeacherUploadRequest> teacherRequests) {
        try {
            List<Teacher> uploadedTeachers = uploadService.uploadTeachers(teacherRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedTeachers.size() + " teacher details.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error uploading teacher details: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading teacher details: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of student details.
     * Only ADMIN can perform this operation.
     *
     * @param studentRequests List of StudentUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadStudents(@RequestBody List<StudentUploadRequest> studentRequests) {
        try {
            List<Student> uploadedStudents = uploadService.uploadStudents(studentRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedStudents.size() + " student details.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error uploading student details: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading student details: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of exam schedules.
     * Only FACULTY or ADMIN can perform this operation.
     *
     * @param examScheduleRequests List of ExamScheduleUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/exam-schedules")
    @PreAuthorize("hasAnyRole('FACULTY', 'ADMIN')")
    public ResponseEntity<String> uploadExamSchedules(@RequestBody List<ExamScheduleUploadRequest> examScheduleRequests) {
        try {
            List<ExamSchedule> uploadedSchedules = uploadService.uploadExamSchedules(examScheduleRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedSchedules.size() + " exam schedules.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error uploading exam schedules: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading exam schedules: " + e.getMessage());
        }
    }
}
