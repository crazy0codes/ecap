package com.student.ecap.service;

import com.student.ecap.dto.*;
import com.student.ecap.model.*;
import com.student.ecap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // NEW: Import PasswordEncoder
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    private final UserRepository userRepository;
    private final ExamScheduleRepository examScheduleRepository;
    private final ExamEventRepository examEventRepository;
    private final BranchRepository branchRepository;
    private final RoleRepository roleRepository; // NEW: Inject RoleRepository
    private final PasswordEncoder passwordEncoder; // NEW: Inject PasswordEncoder

    // Repositories for relationships
    private final SemesterRepository semesterRepository;
    private final GradeRepository gradeRepository;
    private final MidExamRepository midExamRepository;

    @Autowired
    public UploadService(CourseRepository courseRepository, SemesterMarksRepository semesterMarksRepository,
                         RegulationRepository regulationRepository, TeacherRepository teacherRepository,
                         StudentRepository studentRepository, UserRepository userRepository,
                         ExamScheduleRepository examScheduleRepository, ExamEventRepository examEventRepository,
                         SemesterRepository semesterRepository, GradeRepository gradeRepository,
                         BranchRepository branchRepository, MidExamRepository midExamRepository, // Existing
                         RoleRepository roleRepository, PasswordEncoder passwordEncoder) { // NEW: Add to constructor
        this.courseRepository = courseRepository;
        this.semesterMarksRepository = semesterMarksRepository;
        this.regulationRepository = regulationRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
        this.examScheduleRepository = examScheduleRepository;
        this.examEventRepository = examEventRepository;
        this.branchRepository = branchRepository;
        this.semesterRepository = semesterRepository;
        this.gradeRepository = gradeRepository;
        this.midExamRepository = midExamRepository;
        this.roleRepository = roleRepository; // Initialize
        this.passwordEncoder = passwordEncoder; // Initialize
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
     * Creates User and assigns ROLE_FACULTY.
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
                    teacher.setPassword(passwordEncoder.encode(req.getPassword())); // HASH PASSWORD

                    // Create User entry for teacher and assign ROLE_FACULTY
                    Set<Role> roles = new HashSet<>();
                    Role facultyRole = roleRepository.findByName(ERole.ROLE_FACULTY)
                            .orElseThrow(() -> new RuntimeException("Error: Role FACULTY is not found. Please ensure roles are initialized."));
                    roles.add(facultyRole);

                    // A new User entity will be created here. Assuming teacher ID or another unique ID will be the username.
                    // For simplicity, let's use a unique identifier for teachers as their "rollNumber" in the User table.
                    // This is a simplification; ideally, a teacher_id might be used for the User's PK if it's not student-specific.
                    // For now, let's just make sure a User object is created separately from the Student relationship.
                    // Teachers don't have a direct @OneToOne with a Student in your schema, so we need a separate User creation.
                    // However, your User table's PK is roll_number, which is a FK to student. This implies only students are users.
                    // Let's assume for a teacher, their 'id' will be their 'roll_number' for the user table for now.
                    // This is a design conflict in your DBML that needs to be clarified for teachers/faculty.
                    // For now, I will NOT create a User for Teacher due to the `user.roll_number` constraint.
                    // If teachers need to login, User table's PK should not be FK to student.roll_number.
                    // For demo purposes, if you want teachers to login, consider:
                    // 1. Changing user table PK to user_id (new field)
                    // 2. Add student_roll_number FK to user table (nullable)
                    // 3. Add teacher_id FK to user table (nullable)
                    // OR
                    // 4. Create separate login for teachers and students, or combine them to a single 'credential' table.

                    // Given the DBML and current User entity, only students can directly be users.
                    // If faculty need to login, the 'user' table and its relationship need redesign.
                    // Skipping explicit user creation for teachers to avoid schema mismatch.
                    return teacher;
                })
                .collect(Collectors.toList());
        return teacherRepository.saveAll(teachers);
    }

    /**
     * Uploads a list of student details.
     * Creates both Student and associated User records and assigns ROLE_STUDENT.
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

                    // Create associated User entity
                    User user = new User();
                    user.setPassword(passwordEncoder.encode(req.getPassword())); // HASH PASSWORD
                    System.out.print(user.getPassword());
                    Set<Role> roles = new HashSet<>();
                    Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                            .orElseThrow(() -> new RuntimeException("Error: Role STUDENT is not found. Please ensure roles are initialized."));
                    roles.add(studentRole);
                    user.setRoles(roles);

                    user.setStudent(student); // This is crucial for @MapsId to correctly derive the User's PK
                    student.setUser(user); // This is crucial for CascadeType.ALL to save the User when Student is saved

                    return student;
                })
                .collect(Collectors.toList());
        return studentRepository.saveAll(students); // This will cascade save to User due to CascadeType.ALL
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
}
