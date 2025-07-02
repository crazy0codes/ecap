//package com.student.ecap.controller;
//
//import com.student.ecap.dto.*;
//import com.student.ecap.model.*;
//import com.student.ecap.service.UploadService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * REST Controller for uploading various types of data into the system.
// * These endpoints are typically secured and accessible only to administrators.
// */
//@RestController // Marks this class as a REST controller
//@CrossOrigin(origins = "http://localhost:5173")
//@RequestMapping("/api/upload") // Base path for all endpoints in this controller
//public class UploadController {
//
//    private final UploadService uploadService;
//
//    @Autowired // Injects UploadService dependency
//    public UploadController(UploadService uploadService) {
//        this.uploadService = uploadService;
//    }
//
//    /**
//     * Endpoint to upload a list of branches.
//     *
//     * @param branchRequests List of BranchUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/branches") // Maps POST requests to /api/upload/branches
//    public ResponseEntity<String> uploadBranches(@RequestBody List<BranchUploadRequest> branchRequests) {
//        try {
//            List<Branch> uploadedBranches = uploadService.uploadBranches(branchRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedBranches.size() + " branches.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading branches: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Endpoint to upload a list of courses.
//     *
//     * @param courseRequests List of CourseUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/courses") // Maps POST requests to /api/upload/courses
//    public ResponseEntity<String> uploadCourses(@RequestBody List<CourseUploadRequest> courseRequests) {
//        try {
//            List<Course> uploadedCourses = uploadService.uploadCourses(courseRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedCourses.size() + " courses.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading courses: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Endpoint to upload a list of semester exam marks.
//     *
//     * @param semesterMarksRequests List of SemesterMarksUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/semester-marks") // Maps POST requests to /api/upload/semester-marks
//    public ResponseEntity<String> uploadSemesterMarks(@RequestBody List<SemesterMarksUploadRequest> semesterMarksRequests) {
//        try {
//            List<SemesterMarks> uploadedMarks = uploadService.uploadSemesterMarks(semesterMarksRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedMarks.size() + " semester marks.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Error uploading semester marks: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading semester marks: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Endpoint to upload a list of regulations.
//     *
//     * @param regulationRequests List of RegulationUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/regulations") // Maps POST requests to /api/upload/regulations
//    public ResponseEntity<String> uploadRegulations(@RequestBody List<RegulationUploadRequest> regulationRequests) {
//        try {
//            List<Regulation> uploadedRegulations = uploadService.uploadRegulations(regulationRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedRegulations.size() + " regulations.");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading regulations: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Endpoint to upload a list of teacher details.
//     *
//     * @param teacherRequests List of TeacherUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/teachers") // Maps POST requests to /api/upload/teachers
//    public ResponseEntity<String> uploadTeachers(@RequestBody List<TeacherUploadRequest> teacherRequests) {
//        try {
//            List<Teacher> uploadedTeachers = uploadService.uploadTeachers(teacherRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedTeachers.size() + " teacher details.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Error uploading teacher details: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading teacher details: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Endpoint to upload a list of student details.
//     *
//     * @param studentRequests List of StudentUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/students") // Maps POST requests to /api/upload/students
//    public ResponseEntity<String> uploadStudents(@RequestBody List<StudentUploadRequest> studentRequests) {
//        try {
//            List<Student> uploadedStudents = uploadService.uploadStudents(studentRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedStudents.size() + " student details.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Error uploading student details: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading student details: " + e.getMessage());
//        }
//    }
//
//    /**
//     * Endpoint to upload a list of exam schedules.
//     *
//     * @param examScheduleRequests List of ExamScheduleUploadRequest DTOs.
//     * @return ResponseEntity with success message and HTTP status.
//     */
//    @PostMapping("/exam-schedules") // Maps POST requests to /api/upload/exam-schedules
//    public ResponseEntity<String> uploadExamSchedules(@RequestBody List<ExamScheduleUploadRequest> examScheduleRequests) {
//        try {
//            List<ExamSchedule> uploadedSchedules = uploadService.uploadExamSchedules(examScheduleRequests);
//            return ResponseEntity.status(HttpStatus.CREATED)
//                    .body("Successfully uploaded " + uploadedSchedules.size() + " exam schedules.");
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                    .body("Error uploading exam schedules: " + e.getMessage());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Error uploading exam schedules: " + e.getMessage());
//        }
//    }
//}


package com.student.ecap.controller;

import com.student.ecap.dto.*;
import com.student.ecap.model.*;
import com.student.ecap.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for uploading various types of data into the system.
 * All endpoints are now publicly accessible as authentication is removed.
 */
@RestController
@RequestMapping("/api/upload")
@CrossOrigin(origins = "http://localhost:3000")
public class UploadController {

    private final UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * Endpoint to upload a list of branches.
     *
     * @param branchRequests List of BranchUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/branches")
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
     *
     * @param courseRequests List of CourseUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/courses")
    public ResponseEntity<String> uploadCourses(@RequestBody List<CourseUploadRequest> courseRequests) {
        try {
            List<Course> uploadedCourses = uploadService.uploadCourses(courseRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedCourses.size() + " courses.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading courses: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of semester exam marks.
     *
     * @param semesterMarksRequests List of SemesterMarksUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/semester-marks")
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
     *
     * @param regulationRequests List of RegulationUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/regulations")
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
     *
     * @param teacherRequests List of TeacherUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/teachers")
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
     *
     * @param studentRequests List of StudentUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/students")
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
     *
     * @param examScheduleRequests List of ExamScheduleUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/exam-schedules")
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

    /**
     * Endpoint to upload a list of Day entries.
     * @param dayRequests List of DayUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/days")
    public ResponseEntity<String> uploadDays(@RequestBody List<DayUploadRequest> dayRequests) {
        try {
            List<Day> uploadedDays = uploadService.uploadDays(dayRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedDays.size() + " days.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading days: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of Period entries.
     * @param periodRequests List of PeriodUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/periods")
    public ResponseEntity<String> uploadPeriods(@RequestBody List<PeriodUploadRequest> periodRequests) {
        try {
            List<Period> uploadedPeriods = uploadService.uploadPeriods(periodRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedPeriods.size() + " periods.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading periods: " + e.getMessage());
        }
    }

    /**
     * Endpoint to upload a list of Timetable entries.
     * @param timetableRequests List of TimetableUploadRequest DTOs.
     * @return ResponseEntity with success message and HTTP status.
     */
    @PostMapping("/timetable-entries")
    public ResponseEntity<String> uploadTimetableEntries(@RequestBody List<TimetableUploadRequest> timetableRequests) {
        try {
            List<Timetable> uploadedTimetables = uploadService.uploadTimetableEntries(timetableRequests);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Successfully uploaded " + uploadedTimetables.size() + " timetable entries.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error uploading timetable entries: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error uploading timetable entries: " + e.getMessage());
        }
    }
}
