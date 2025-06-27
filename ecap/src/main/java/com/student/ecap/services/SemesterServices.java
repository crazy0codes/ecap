package com.student.ecap.services;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.respository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterServices {

    @Autowired
    private SemesterRepository semesterRepository;

    // Save a new semester (only if semester_id doesn't already exist)
    public String saveSemester(SemesterEntity semester) {
        if (semester.getSemesterId() == null || semester.getSemesterNumber() == null) {
            return "Invalid semester data: Semester ID and Semester Number are required.";
        }

        boolean exists = semesterRepository.existsById(semester.getSemesterId());

        if (exists) {
            return "Semester already exists with ID: " + semester.getSemesterId();
        }

        semesterRepository.save(semester);
        return "Semester uploaded successfully.";
    }

    // Get all semesters
    public List<SemesterEntity> getAllSemesters() {
        return semesterRepository.findAll();
    }

    // Get semester by semester ID (primary key)
    public Optional<SemesterEntity> getSemesterById(int semesterId) {
        return semesterRepository.findById(semesterId);
    }

    // Optional: Get semester by semester number (e.g., 1 to 8)
    public Optional<SemesterEntity> getBySemesterNumber(int semesterNumber) {
        return semesterRepository.findBySemesterNumber(semesterNumber);
    }
    // Optional: Delete semester by ID
    public String deleteSemester(int semesterId) {
        if (!semesterRepository.existsById(semesterId)) {
            return "Semester with ID " + semesterId + " not found.";
        }
        semesterRepository.deleteById(semesterId);
        return "Semester deleted successfully.";
    }
}
