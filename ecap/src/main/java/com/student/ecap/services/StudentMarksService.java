package com.student.ecap.services;

import com.student.ecap.entities.StudentMarksEntity;
import com.student.ecap.repository.StudentMarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentMarksService {

    @Autowired
    private StudentMarksRepository studentMarksRepository;

    public String insertMarks(StudentMarksEntity newMarks) {
        Optional<StudentMarksEntity> existing = studentMarksRepository
                .findByRollnoAndSemNo(newMarks.getRollno(), newMarks.getSemNo());

        if (existing.isPresent()) {
            return "Marks already exist for Roll No: " + newMarks.getRollno() + " and Sem: " + newMarks.getSemNo();
        } else {
            studentMarksRepository.save(newMarks);
            return "Marks inserted successfully.";
        }
    }

    public Optional<StudentMarksEntity> getMarks(String rollno, int semNo) {
        return studentMarksRepository.findByRollnoAndSemNo(rollno, semNo);
    }
}
