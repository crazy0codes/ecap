package com.student.ecap.service;

import com.student.ecap.dto.AttendanceRecordRequest;
import com.student.ecap.dto.AttendanceRecordResponse;
import com.student.ecap.dto.AttendanceSessionCreateRequest;
import com.student.ecap.dto.AttendanceSessionResponse;
import com.student.ecap.dto.StudentAttendanceSummaryResponse;
import com.student.ecap.model.*;
import com.student.ecap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for managing attendance sessions and records.
 */
@Service
public class AttendanceService {

    private final AttendanceSessionRepository attendanceSessionRepository;
    private final AttendanceRecordRepository attendanceRecordRepository;
    private final TeacherRepository teacherRepository;
    private final CourseRepository courseRepository;
    private final BranchRepository branchRepository;
    private final SemesterRepository semesterRepository;
    private final PeriodRepository periodRepository;
    private final DayRepository dayRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AttendanceService(AttendanceSessionRepository attendanceSessionRepository,
                             AttendanceRecordRepository attendanceRecordRepository,
                             TeacherRepository teacherRepository, CourseRepository courseRepository,
                             BranchRepository branchRepository, SemesterRepository semesterRepository,
                             PeriodRepository periodRepository, DayRepository dayRepository,
                             StudentRepository studentRepository) {
        this.attendanceSessionRepository = attendanceSessionRepository;
        this.attendanceRecordRepository = attendanceRecordRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
        this.branchRepository = branchRepository;
        this.semesterRepository = semesterRepository;
        this.periodRepository = periodRepository;
        this.dayRepository = dayRepository;
        this.studentRepository = studentRepository;
    }

    /**
     * Creates a new attendance session.
     * @param request DTO containing session details.
     * @return Optional of AttendanceSessionResponse if created, empty if session already exists or data invalid.
     */
    @Transactional
    public Optional<AttendanceSessionResponse> createAttendanceSession(AttendanceSessionCreateRequest request) {
        // Check if a session with these exact details already exists
        Optional<AttendanceSession> existingSession = attendanceSessionRepository
                .findByTeacher_IdAndCourse_CourseIdAndBranch_BranchIdAndSemester_SemesterIdAndPeriod_IdAndDay_IdAndDate(
                        request.getTeacherId(), request.getCourseId(), request.getBranchId(),
                        request.getSemesterId(), request.getPeriodId(), request.getDayId(), request.getDate()
                );

        if (existingSession.isPresent()) {
            // Session already exists, return its details
            AttendanceSession session = existingSession.get();
            return Optional.of(new AttendanceSessionResponse(
                    session.getId(),
                    session.getTeacher().getFirstName() + " " + session.getTeacher().getLastName(),
                    session.getCourse().getCourseName(),
                    session.getBranch().getBranchName(),
                    session.getSemester().getSemesterNumber(),
                    session.getPeriod().getPeriodNumber(),
                    session.getDay().getName(),
                    session.getDate()
            ));
        }

        // Fetch required entities
        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found for ID: " + request.getTeacherId()));
        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Course not found for ID: " + request.getCourseId()));
        Branch branch = branchRepository.findById(request.getBranchId())
                .orElseThrow(() -> new IllegalArgumentException("Branch not found for ID: " + request.getBranchId()));
        Semester semester = semesterRepository.findById(request.getSemesterId())
                .orElseThrow(() -> new IllegalArgumentException("Semester not found for ID: " + request.getSemesterId()));
        Period period = periodRepository.findById(request.getPeriodId())
                .orElseThrow(() -> new IllegalArgumentException("Period not found for ID: " + request.getPeriodId()));
        Day day = dayRepository.findById(request.getDayId())
                .orElseThrow(() -> new IllegalArgumentException("Day not found for ID: " + request.getDayId()));

        AttendanceSession newSession = new AttendanceSession();
        newSession.setTeacher(teacher);
        newSession.setCourse(course);
        newSession.setBranch(branch);
        newSession.setSemester(semester);
        newSession.setPeriod(period);
        newSession.setDay(day);
        newSession.setDate(request.getDate());

        AttendanceSession savedSession = attendanceSessionRepository.save(newSession);

        return Optional.of(new AttendanceSessionResponse(
                savedSession.getId(),
                savedSession.getTeacher().getFirstName() + " " + savedSession.getTeacher().getLastName(),
                savedSession.getCourse().getCourseName(),
                savedSession.getBranch().getBranchName(),
                savedSession.getSemester().getSemesterNumber(),
                savedSession.getPeriod().getPeriodNumber(),
                savedSession.getDay().getName(),
                savedSession.getDate()
        ));
    }

    /**
     * Marks attendance for multiple students in a given session.
     * @param sessionId The ID of the attendance session.
     * @param attendanceRecords List of AttendanceRecordRequest DTOs.
     * @return List of created AttendanceRecordResponse DTOs.
     */
    @Transactional
    public List<AttendanceRecordResponse> markAttendance(Integer sessionId, List<AttendanceRecordRequest> attendanceRecords) {
        AttendanceSession session = attendanceSessionRepository.findById(sessionId)
                .orElseThrow(() -> new IllegalArgumentException("Attendance Session not found for ID: " + sessionId));

        return attendanceRecords.stream()
                .map(req -> {
                    Student student = studentRepository.findById(req.getStudentRollNumber())
                            .orElseThrow(() -> new IllegalArgumentException("Student not found for roll number: " + req.getStudentRollNumber()));

                    // Check if record already exists for this student in this session
                    Optional<AttendanceRecord> existingRecord = attendanceRecordRepository
                            .findByAttendanceSession_IdAndStudent_RollNumber(sessionId, req.getStudentRollNumber());

                    AttendanceRecord record;
                    if (existingRecord.isPresent()) {
                        record = existingRecord.get();
                        record.setStatus(req.getStatus()); // Update existing record
                    } else {
                        record = new AttendanceRecord();
                        record.setAttendanceSession(session);
                        record.setStudent(student);
                        record.setStatus(req.getStatus());
                    }
                    AttendanceRecord savedRecord = attendanceRecordRepository.save(record);
                    return new AttendanceRecordResponse(
                            savedRecord.getId(),
                            savedRecord.getStudent().getRollNumber(),
                            savedRecord.getStudent().getName(),
                            savedRecord.getStatus()
                    );
                })
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all attendance records for a specific session.
     * @param sessionId The ID of the attendance session.
     * @return List of AttendanceRecordResponse DTOs.
     */
    @Transactional(readOnly = true)
    public List<AttendanceRecordResponse> getAttendanceRecordsBySession(Integer sessionId) {
        List<AttendanceRecord> records = attendanceRecordRepository.findByAttendanceSession_Id(sessionId);
        return records.stream()
                .map(record -> new AttendanceRecordResponse(
                        record.getId(),
                        record.getStudent().getRollNumber(),
                        record.getStudent().getName(),
                        record.getStatus()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a summary of attendance for a specific student.
     * @param studentRollNumber The roll number of the student.
     * @return List of StudentAttendanceSummaryResponse DTOs.
     */
    @Transactional(readOnly = true)
    public List<StudentAttendanceSummaryResponse> getStudentAttendanceSummary(String studentRollNumber) {
        List<AttendanceRecord> records = attendanceRecordRepository.findByStudent_RollNumber(studentRollNumber);

        return records.stream()
                .map(record -> new StudentAttendanceSummaryResponse(
                        record.getAttendanceSession().getId(),
                        record.getAttendanceSession().getCourse().getCourseName(),
                        record.getAttendanceSession().getTeacher().getFirstName() + " " + record.getAttendanceSession().getTeacher().getLastName(),
                        record.getAttendanceSession().getDate(),
                        record.getAttendanceSession().getPeriod().getPeriodNumber(),
                        record.getAttendanceSession().getDay().getName(),
                        record.getStatus()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all attendance sessions for a specific branch, semester, and date.
     * @param branchId ID of the branch.
     * @param semesterId ID of the semester.
     * @param date The actual date of the session.
     * @return List of AttendanceSessionResponse DTOs.
     */
    @Transactional(readOnly = true)
    public List<AttendanceSessionResponse> getAttendanceSessionsByDate(Integer branchId, Integer semesterId, LocalDate date) {
        List<AttendanceSession> sessions = attendanceSessionRepository.findByBranch_BranchIdAndSemester_SemesterIdAndDate(branchId, semesterId, date);
        return sessions.stream()
                .map(session -> new AttendanceSessionResponse(
                        session.getId(),
                        session.getTeacher().getFirstName() + " " + session.getTeacher().getLastName(),
                        session.getCourse().getCourseName(),
                        session.getBranch().getBranchName(),
                        session.getSemester().getSemesterNumber(),
                        session.getPeriod().getPeriodNumber(),
                        session.getDay().getName(),
                        session.getDate()
                ))
                .collect(Collectors.toList());
    }
}
