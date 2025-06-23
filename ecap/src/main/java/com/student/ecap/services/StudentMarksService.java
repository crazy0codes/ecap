package com.student.ecap.services;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.respository.SemesterRepository;
import com.student.ecap.entities.StudentMarksEntity;
import com.student.ecap.respository.StudentMarksRepository;
import com.student.ecap.respository.StudetailsRepo;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentMarksService {

    @Autowired
    private StudentMarksRepository studentMarksRepository;
    @Autowired
    private SemesterRepository SemesterRepository;
    @Autowired
    private StudetailsRepo StudetailsRepo;

    public String insertMarks(@NotNull StudentMarksEntity marks) {

        // 1. Check if student is registered
        Optional<StudetailsEntity> existingStudent = StudetailsRepo.findByRollno(marks.getRollno());
        if (existingStudent.isEmpty()) {
            return "Invalid Roll Number: " + marks.getRollno();
        }

        // 2. Check if semester exists
        Optional<SemesterEntity> semester = SemesterRepository.findBySemNo(marks.getSemNo());
        if (semester.isEmpty()) {
            return "Semester not found for semNo: " + marks.getSemNo();
        }

        // 3. Check if marks already inserted
        Optional<StudentMarksEntity> existingMarks = studentMarksRepository
                .findByRollnoAndSemNo(marks.getRollno(), marks.getSemNo());
        if (existingMarks.isPresent()) {
            return "Marks already exist for Roll No: " + marks.getRollno() + " and Sem: " + marks.getSemNo();
        }
        // 4. Check if all subjects are included in the marks map
        SemesterEntity sem = semester.get();
        List<String> expectedSubjects = Arrays.asList(
                sem.getSubject1(), sem.getSubject2(), sem.getSubject3(),
                sem.getSubject4(), sem.getSubject5()
        );
        if (!marks.getMarks().keySet().containsAll(expectedSubjects)) {
            return "Marks must be given for all subjects: " + expectedSubjects;
        }

        // ✅ 5. Save the marks
        studentMarksRepository.save(marks);
        return "Marks inserted successfully with subject names.";
    }


    public Map<String, Object> getMarksAndGrade(String rollno, int semNo) {
        Optional<StudentMarksEntity> optionalMarks = studentMarksRepository.findByRollnoAndSemNo(rollno, semNo);

        if (optionalMarks.isPresent()) {
            StudentMarksEntity marksEntity = optionalMarks.get();
            Map<String, Integer> marks = marksEntity.getMarks();

            int total = marks.values().stream().mapToInt(Integer::intValue).sum();
            double percentage = total / (double) marks.size();

            String grade;
            if (percentage >= 90) grade = "A";
            else if (percentage >= 80) grade = "B";
            else if (percentage >= 70) grade = "C";
            else if (percentage >= 60) grade = "D";
            else grade = "F";

            Map<String, Object> result = new LinkedHashMap<>(); // ✅ preserves order
            result.put("rollno", marksEntity.getRollno());
            result.put("semNo", marksEntity.getSemNo());
            result.putAll(marks); // marks inserted after rollno and semNo
            result.put("total", total);
            result.put("percentage", percentage);
            result.put("grade", grade);
            return result;
        }

        return null;
    }
}

