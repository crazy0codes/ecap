package com.student.ecap.service;

import com.student.ecap.dto.*;
import com.student.ecap.model.*;
import com.student.ecap.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.student.ecap.repository.ExamEventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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

    // Repositories for relationships
    private final SemesterRepository semesterRepository;
    private final GradeRepository gradeRepository;
    private final MidExamRepository midExamRepository;

    @Autowired
    public UploadService(CourseRepository courseRepository, SemesterMarksRepository semesterMarksRepository,
                         RegulationRepository regulationRepository, TeacherRepository teacherRepository,
                         StudentRepository studentRepository, UserRepository userRepository,
                         ExamScheduleRepository examScheduleRepository,
                         ExamEventRepository examEventRepository,
                         SemesterRepository semesterRepository, GradeRepository gradeRepository,
                         BranchRepository branchRepository,
                         MidExamRepository midExamRepository) {
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

                    // Manually set the ID for the composite key if it was not done automatically by the DTO mapping
                    // This is crucial for @EmbeddedId and @MapsId when creating new entities
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
                    teacher.setPassword(req.getPassword()); // In production, hash passwords before saving!
                    return teacher;
                })
                .collect(Collectors.toList());
        return teacherRepository.saveAll(teachers);
    }

    /**
     * Uploads a list of student details.
     * Creates both Student and associated User records.
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
                    // REMOVED: user.setRollNumber(req.getRollNumber()); // Let @MapsId manage this via the Student relationship
                    user.setPassword(req.getPassword()); // In production, hash passwords!
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
