package com.student.ecap.service;

import com.student.ecap.dto.*;
import com.student.ecap.model.*;
import com.student.ecap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling bulk data uploads for various entities.
 * Each method processes a list of DTOs and saves them to the database.
 */
@Service
public class UploadService {

    private final CourseRepository courseRepository;
    private final SemesterMarksRepository semesterMarksRepository;
    private final RegulationRepository regulationRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final ExamScheduleRepository examScheduleRepository;
    private final ExamEventRepository examEventRepository;
    private final BranchRepository branchRepository;
    private final SemesterRepository semesterRepository;
    private final GradeRepository gradeRepository;
    private final MidExamRepository midExamRepository;
    private final DayRepository dayRepository;
    private final PeriodRepository periodRepository;
    private final TimetableRepository timetableRepository;
    private final AttendanceSessionRepository attendanceSessionRepository; // NEW
    private final AttendanceRecordRepository attendanceRecordRepository; // NEW

    @Autowired
    public UploadService(CourseRepository courseRepository, SemesterMarksRepository semesterMarksRepository,
                         RegulationRepository regulationRepository, TeacherRepository teacherRepository,
                         StudentRepository studentRepository, ExamScheduleRepository examScheduleRepository,
                         ExamEventRepository examEventRepository, SemesterRepository semesterRepository,
                         GradeRepository gradeRepository, BranchRepository branchRepository,
                         MidExamRepository midExamRepository, DayRepository dayRepository,
                         PeriodRepository periodRepository, TimetableRepository timetableRepository,
                         AttendanceSessionRepository attendanceSessionRepository, // NEW
                         AttendanceRecordRepository attendanceRecordRepository) { // NEW
        this.courseRepository = courseRepository;
        this.semesterMarksRepository = semesterMarksRepository;
        this.regulationRepository = regulationRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.examScheduleRepository = examScheduleRepository;
        this.examEventRepository = examEventRepository;
        this.branchRepository = branchRepository;
        this.semesterRepository = semesterRepository;
        this.gradeRepository = gradeRepository;
        this.midExamRepository = midExamRepository;
        this.dayRepository = dayRepository;
        this.periodRepository = periodRepository;
        this.timetableRepository = timetableRepository;
        this.attendanceSessionRepository = attendanceSessionRepository; // Initialize
        this.attendanceRecordRepository = attendanceRecordRepository; // Initialize
    }

    /**
     * Uploads a list of branches.
     * @param branchRequests List of BranchUploadRequest DTOs.
     * @return List of saved Branch entities.
     */
    @Transactional
    public List<Branch> uploadBranches(List<BranchUploadRequest> branchRequests) {
        List<Branch> branches = branchRequests.stream()
                .map(req -> {
                    Branch branch = new Branch();
                    branch.setBranchName(req.getBranchName());
                    return branch;
                })
                .collect(Collectors.toList());
        return branchRepository.saveAll(branches);
    }

    /**
     * Uploads a list of courses.
     * @param courseRequests List of CourseUploadRequest DTOs.
     * @return List of saved Course entities.
     */
    @Transactional
    public List<Course> uploadCourses(List<CourseUploadRequest> courseRequests) {
        List<Course> courses = courseRequests.stream()
                .map(req -> {
                    Course course = new Course();
                    course.setCourseCode(req.getCourseCode());
                    course.setCourseName(req.getCourseName());
                    course.setCredits(req.getCredits());
                    return course;
                })
                .collect(Collectors.toList());
        return courseRepository.saveAll(courses);
    }

    /**
     * Uploads a list of semester exam marks.
     * Ensures all related entities (Student, Course, Semester, Grade) exist.
     * @param semesterMarksRequests List of SemesterMarksUploadRequest DTOs.
     * @return List of saved SemesterMarks entities.
     */
    @Transactional
    public List<SemesterMarks> uploadSemesterMarks(List<SemesterMarksUploadRequest> semesterMarksRequests) {
        List<SemesterMarks> semesterMarksList = semesterMarksRequests.stream()
                .map(req -> {
                    SemesterMarks semesterMarks = new SemesterMarks();

                    // Fetch and set related entities
                    Student student = studentRepository.findById(req.getRollNumber())
                            .orElseThrow(() -> new IllegalArgumentException("Student not found for rollNumber: " + req.getRollNumber()));
                    semesterMarks.setStudent(student);

                    Course course = courseRepository.findById(req.getCourseId())
                            .orElseThrow(() -> new IllegalArgumentException("Course not found for courseId: " + req.getCourseId()));
                    semesterMarks.setCourse(course);

                    Semester semester = semesterRepository.findById(req.getSemesterId())
                            .orElseThrow(() -> new IllegalArgumentException("Semester not found for semesterId: " + req.getSemesterId()));
                    semesterMarks.setSemester(semester);

                    Grade grade = gradeRepository.findById(req.getGrade())
                            .orElseThrow(() -> new IllegalArgumentException("Grade not found for grade: " + req.getGrade()));
                    semesterMarks.setGrade(grade);

                    semesterMarks.setMarksObtained(req.getMarksObtained());

                    SemesterMarks.SemesterMarksId semesterMarksId = new SemesterMarks.SemesterMarksId();
                    semesterMarksId.setRollNumber(req.getRollNumber());
                    semesterMarksId.setCourseId(req.getCourseId());
                    semesterMarksId.setSemesterId(req.getSemesterId());
                    semesterMarks.setId(semesterMarksId);

                    return semesterMarks;
                })
                .collect(Collectors.toList());
        return semesterMarksRepository.saveAll(semesterMarksList);
    }

    /**
     * Uploads a list of regulations.
     * @param regulationRequests List of RegulationUploadRequest DTOs.
     * @return List of saved Regulation entities.
     */
    @Transactional
    public List<Regulation> uploadRegulations(List<RegulationUploadRequest> regulationRequests) {
        List<Regulation> regulations = regulationRequests.stream()
                .map(req -> new Regulation(req.getRegulationId()))
                .collect(Collectors.toList());
        return regulationRepository.saveAll(regulations);
    }

    /**
     * Uploads a list of teacher details.
     * Ensures all related entities (Branch, Course) exist.
     * @param teacherRequests List of TeacherUploadRequest DTOs.
     * @return List of saved Teacher entities.
     */
    @Transactional
    public List<Teacher> uploadTeachers(List<TeacherUploadRequest> teacherRequests) {
        List<Teacher> teachers = teacherRequests.stream()
                .map(req -> {
                    Teacher teacher = new Teacher();

                    Branch branch = branchRepository.findById(req.getBranchId())
                            .orElseThrow(() -> new IllegalArgumentException("Branch not found for branchId: " + req.getBranchId()));
                    teacher.setBranch(branch);

                    Course course = courseRepository.findById(req.getCourseId())
                            .orElseThrow(() -> new IllegalArgumentException("Course not found for courseId: " + req.getCourseId()));
                    teacher.setCourse(course);

                    teacher.setFirstName(req.getFirstName());
                    teacher.setLastName(req.getLastName());
                    // Password field removed from Teacher model as it's not used for authentication without Spring Security
                    // teacher.setPassword(req.getPassword()); // If you want to store it as plain text for record keeping
                    return teacher;
                })
                .collect(Collectors.toList());
        return teacherRepository.saveAll(teachers);
    }

    /**
     * Uploads a list of student details.
     * Creates Student records. User creation and role assignment removed.
     * Ensures related entities (Regulation, Branch) exist.
     * @param studentRequests List of StudentUploadRequest DTOs.
     * @return List of saved Student entities.
     */
    @Transactional
    public List<Student> uploadStudents(List<StudentUploadRequest> studentRequests) {
        List<Student> students = studentRequests.stream()
                .map(req -> {
                    Student student = new Student();
                    student.setRollNumber(req.getRollNumber());
                    student.setName(req.getName());

                    Regulation regulation = regulationRepository.findById(req.getRegulationId())
                            .orElseThrow(() -> new IllegalArgumentException("Regulation not found for regulationId: " + req.getRegulationId()));
                    student.setRegulation(regulation);

                    Branch branch = branchRepository.findById(req.getBranchId())
                            .orElseThrow(() -> new IllegalArgumentException("Branch not found for branchId: " + req.getBranchId()));
                    student.setBranch(branch);

                    student.setAddress(req.getAddress());
                    student.setEmail(req.getEmail());
                    student.setMobileno(req.getMobileno());
                    student.setBloodgroup(req.getBloodgroup());
                    student.setFatherName(req.getFatherName());
                    student.setMotherName(req.getMotherName());

                    // User creation and role assignment removed as authentication is removed
                    return student;
                })
                .collect(Collectors.toList());
        return studentRepository.saveAll(students);
    }

    /**
     * Uploads a list of exam schedules.
     * Creates an ExamSchedule and an associated ExamEvent.
     * Ensures related entities (Semester, Regulation) exist.
     * @param examScheduleRequests List of ExamScheduleUploadRequest DTOs.
     * @return List of saved ExamSchedule entities.
     */
    @Transactional
    public List<ExamSchedule> uploadExamSchedules(List<ExamScheduleUploadRequest> examScheduleRequests) {
        return examScheduleRequests.stream()
                .map(req -> {
                    ExamSchedule examSchedule = new ExamSchedule();

                    Semester semester = semesterRepository.findById(req.getSemesterId())
                            .orElseThrow(() -> new IllegalArgumentException("Semester not found for semesterId: " + req.getSemesterId()));
                    examSchedule.setSemester(semester);

                    Regulation regulation = regulationRepository.findById(req.getRegulationId())
                            .orElseThrow(() -> new IllegalArgumentException("Regulation not found for regulationId: " + req.getRegulationId()));
                    examSchedule.setRegulation(regulation);

                    ExamSchedule savedSchedule = examScheduleRepository.save(examSchedule); // Save schedule first to get ID

                    // Create and save associated ExamEvent
                    ExamEvent examEvent = new ExamEvent();
                    examEvent.setSchedule(savedSchedule); // Link to the newly saved schedule
                    examEvent.setStartDate(req.getStartDate());
                    examEvent.setEndDate(req.getEndDate());
                    examEvent.setDescription(req.getDescription());
                    examEventRepository.save(examEvent);

                    return savedSchedule;
                })
                .collect(Collectors.toList());
    }

    /**
     * Uploads a list of Day entries.
     * @param dayRequests List of DayUploadRequest DTOs.
     * @return List of saved Day entities.
     */
    @Transactional
    public List<Day> uploadDays(List<DayUploadRequest> dayRequests) {
        List<Day> days = dayRequests.stream()
                .map(req -> new Day(req.getName()))
                .collect(Collectors.toList());
        return dayRepository.saveAll(days);
    }

    /**
     * Uploads a list of Period entries.
     * @param periodRequests List of PeriodUploadRequest DTOs.
     * @return List of saved Period entities.
     */
    @Transactional
    public List<Period> uploadPeriods(List<PeriodUploadRequest> periodRequests) {
        List<Period> periods = periodRequests.stream()
                .map(req -> new Period(req.getPeriodNumber()))
                .collect(Collectors.toList());
        return periodRepository.saveAll(periods);
    }

    /**
     * Uploads a list of Timetable entries.
     * Ensures all related entities (Branch, Semester, Day, Period, Course) exist.
     * @param timetableRequests List of TimetableUploadRequest DTOs.
     * @return List of saved Timetable entities.
     */
    @Transactional
    public List<Timetable> uploadTimetableEntries(List<TimetableUploadRequest> timetableRequests) {
        List<Timetable> timetables = timetableRequests.stream()
                .map(req -> {
                    Timetable timetable = new Timetable();

                    Branch branch = branchRepository.findById(req.getBranchId())
                            .orElseThrow(() -> new IllegalArgumentException("Branch not found for branchId: " + req.getBranchId()));
                    timetable.setBranch(branch);

                    Semester semester = semesterRepository.findById(req.getSemesterId())
                            .orElseThrow(() -> new IllegalArgumentException("Semester not found for semesterId: " + req.getSemesterId()));
                    timetable.setSemester(semester);

                    Day day = dayRepository.findById(req.getDayId())
                            .orElseThrow(() -> new IllegalArgumentException("Day not found for dayId: " + req.getDayId()));
                    timetable.setDay(day);

                    Period period = periodRepository.findById(req.getPeriodId())
                            .orElseThrow(() -> new IllegalArgumentException("Period not found for periodId: " + req.getPeriodId()));
                    timetable.setPeriod(period);

                    Course course = courseRepository.findById(req.getCourseId())
                            .orElseThrow(() -> new IllegalArgumentException("Course not found for courseId: " + req.getCourseId()));
                    timetable.setCourse(course);

                    return timetable;
                })
                .collect(Collectors.toList());
        return timetableRepository.saveAll(timetables);
    }

    /**
     * Uploads a list of Attendance Session entries.
     * @param sessionRequests List of AttendanceSessionCreateRequest DTOs.
     * @return List of saved AttendanceSession entities.
     */
    @Transactional
    public List<AttendanceSession> uploadAttendanceSessions(List<AttendanceSessionCreateRequest> sessionRequests) {
        List<AttendanceSession> sessions = sessionRequests.stream()
                .map(req -> {
                    AttendanceSession session = new AttendanceSession();

                    Teacher teacher = teacherRepository.findById(req.getTeacherId())
                            .orElseThrow(() -> new IllegalArgumentException("Teacher not found for ID: " + req.getTeacherId()));
                    session.setTeacher(teacher);

                    Course course = courseRepository.findById(req.getCourseId())
                            .orElseThrow(() -> new IllegalArgumentException("Course not found for ID: " + req.getCourseId()));
                    session.setCourse(course);

                    Branch branch = branchRepository.findById(req.getBranchId())
                            .orElseThrow(() -> new IllegalArgumentException("Branch not found for ID: " + req.getBranchId()));
                    session.setBranch(branch);

                    Semester semester = semesterRepository.findById(req.getSemesterId())
                            .orElseThrow(() -> new IllegalArgumentException("Semester not found for ID: " + req.getSemesterId()));
                    session.setSemester(semester);

                    Period period = periodRepository.findById(req.getPeriodId())
                            .orElseThrow(() -> new IllegalArgumentException("Period not found for ID: " + req.getPeriodId()));
                    session.setPeriod(period);

                    Day day = dayRepository.findById(req.getDayId())
                            .orElseThrow(() -> new IllegalArgumentException("Day not found for ID: " + req.getDayId()));
                    session.setDay(day);

                    session.setDate(req.getDate());

                    return session;
                })
                .collect(Collectors.toList());
        return attendanceSessionRepository.saveAll(sessions);
    }

    /**
     * Uploads a list of Attendance Record entries.
     * @param recordRequests List of AttendanceRecordRequest DTOs.
     * @return List of saved AttendanceRecord entities.
     */
    @Transactional
    public List<AttendanceRecord> uploadAttendanceRecords(List<AttendanceRecordUploadRequest> recordRequests) {
        List<AttendanceRecord> records = recordRequests.stream()
                .map(req -> {
                    AttendanceRecord record = new AttendanceRecord();

                    AttendanceSession session = attendanceSessionRepository.findById(req.getAttendanceSessionId())
                            .orElseThrow(() -> new IllegalArgumentException("Attendance Session not found for ID: " + req.getAttendanceSessionId()));
                    record.setAttendanceSession(session);

                    Student student = studentRepository.findById(req.getStudentRollNumber())
                            .orElseThrow(() -> new IllegalArgumentException("Student not found for roll number: " + req.getStudentRollNumber()));
                    record.setStudent(student);

                    record.setStatus(req.getStatus());

                    return record;
                })
                .collect(Collectors.toList());
        return attendanceRecordRepository.saveAll(records);
    }
}
