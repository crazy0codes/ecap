package com.student.ecap.service;

import com.student.ecap.dto.StudentDetailsResponse;
import com.student.ecap.dto.StudentSemesterMarkResponse; // New import
import com.student.ecap.dto.StudentUpdateRequest;
import com.student.ecap.model.Branch;
import com.student.ecap.model.Regulation;
import com.student.ecap.model.SemesterMarks; // New import
import com.student.ecap.model.Student;
import com.student.ecap.repository.BranchRepository;
import com.student.ecap.repository.RegulationRepository;
import com.student.ecap.repository.SemesterMarksRepository; // New import
import com.student.ecap.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class for handling student-related business logic,
 * such as retrieving and updating student profiles, and now retrieving marks.
 */
@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RegulationRepository regulationRepository;
    private final BranchRepository branchRepository;
    private final SemesterMarksRepository semesterMarksRepository; // Inject the new repository

    @Autowired
    public StudentService(StudentRepository studentRepository, RegulationRepository regulationRepository,
                          BranchRepository branchRepository, SemesterMarksRepository semesterMarksRepository) { // Add to constructor
        this.studentRepository = studentRepository;
        this.regulationRepository = regulationRepository;
        this.branchRepository = branchRepository;
        this.semesterMarksRepository = semesterMarksRepository; // Initialize
    }

    /**
     * Retrieves student details by roll number.
     *
     * @param rollNumber The roll number of the student.
     * @return StudentDetailsResponse DTO or empty Optional if not found.
     */
    @Transactional(readOnly = true)
    public Optional<StudentDetailsResponse> getStudentDetails(String rollNumber) {
        return studentRepository.findById(rollNumber)
                .map(student -> new StudentDetailsResponse(
                        student.getRollNumber(),
                        student.getName(),
                        student.getRegulation() != null ? student.getRegulation().getRegulationId() : null,
                        student.getBranch() != null ? student.getBranch().getBranchId() : null,
                        student.getAddress(),
                        student.getEmail(),
                        student.getMobileno(),
                        student.getBloodgroup(),
                        student.getFatherName(),
                        student.getMotherName()
                ));
    }

    /**
     * Updates specific details for a student.
     *
     * @param rollNumber The roll number of the student to update.
     * @param updateRequest DTO containing the fields to update.
     * @return Optional containing the updated StudentDetailsResponse DTO, or empty if student not found.
     */
    @Transactional
    public Optional<StudentDetailsResponse> updateStudentDetails(String rollNumber, StudentUpdateRequest updateRequest) {
        Optional<Student> studentOptional = studentRepository.findById(rollNumber);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            if (updateRequest.getAddress() != null) {
                student.setAddress(updateRequest.getAddress());
            }
            if (updateRequest.getEmail() != null) {
                student.setEmail(updateRequest.getEmail());
            }
            if (updateRequest.getMobileno() != null) {
                student.setMobileno(updateRequest.getMobileno());
            }
            if (updateRequest.getBloodgroup() != null) {
                student.setBloodgroup(updateRequest.getBloodgroup());
            }

            Student updatedStudent = studentRepository.save(student);

            return Optional.of(new StudentDetailsResponse(
                    updatedStudent.getRollNumber(),
                    updatedStudent.getName(),
                    updatedStudent.getRegulation() != null ? updatedStudent.getRegulation().getRegulationId() : null,
                    updatedStudent.getBranch() != null ? updatedStudent.getBranch().getBranchId() : null,
                    updatedStudent.getAddress(),
                    updatedStudent.getEmail(),
                    updatedStudent.getMobileno(),
                    updatedStudent.getBloodgroup(),
                    updatedStudent.getFatherName(),
                    updatedStudent.getMotherName()
            ));
        }
        return Optional.empty();
    }

    /**
     * Retrieves all semester marks for a given student and semester.
     *
     * @param rollNumber The roll number of the student.
     * @param semesterId The ID of the semester.
     * @return A list of StudentSemesterMarkResponse DTOs. Returns an empty list if no marks are found.
     */
    @Transactional(readOnly = true)
    public List<StudentSemesterMarkResponse> getSemesterMarksForStudent(String rollNumber, Integer semesterId) {
        List<SemesterMarks> semesterMarks = semesterMarksRepository.findByStudent_RollNumberAndSemester_SemesterId(rollNumber, semesterId);

        return semesterMarks.stream()
                .map(mark -> new StudentSemesterMarkResponse(
                        mark.getCourse().getCourseCode(),
                        mark.getCourse().getCourseName(),
                        mark.getCourse().getCredits(),
                        mark.getMarksObtained(),
                        mark.getGrade().getGrade(), // Assuming Grade entity has getGrade() for the string value
                        mark.getSemester().getSemesterNumber()
                ))
                .collect(Collectors.toList());
    }
}
