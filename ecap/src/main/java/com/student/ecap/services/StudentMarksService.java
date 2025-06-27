package com.student.ecap.services;

import com.student.ecap.entities.StudentMarksEntity;
import com.student.ecap.respository.StudentMarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentMarksService {

    @Autowired
    private StudentMarksRepository studentMarksRepository;

    // ✅ Insert new marks
    public String insertMarks(StudentMarksEntity marks) {
        Optional<StudentMarksEntity> existing = studentMarksRepository.findByRollnoAndSemNo(marks.getRollno(), marks.getSemNo());
        if (existing.isPresent()) {
            return "Marks already exist for Roll No: " + marks.getRollno() + ", Sem: " + marks.getSemNo();
        }
        studentMarksRepository.save(marks);
        return "Marks uploaded successfully";
    }

    // ✅ Edit/Update marks
    public String updateMarks(String rollno, int semNo, StudentMarksEntity updatedMarks) {
        Optional<StudentMarksEntity> existingOpt = studentMarksRepository.findByRollnoAndSemNo(rollno, semNo);
        if (existingOpt.isEmpty()) {
            return "No existing marks found for Roll No: " + rollno + ", Sem: " + semNo;
        }

        StudentMarksEntity existing = existingOpt.get();
        existing.setMid1(updatedMarks.getMid1());
        existing.setMid2(updatedMarks.getMid2());
        existing.setSemMarks(updatedMarks.getSemMarks());
        existing.setRegulation(updatedMarks.getRegulation());

        studentMarksRepository.save(existing);
        return "Marks updated successfully";
    }

    // ✅ Delete marks
    public String deleteMarks(String rollno, int semNo) {
        Optional<StudentMarksEntity> existing = studentMarksRepository.findByRollnoAndSemNo(rollno, semNo);
        if (existing.isEmpty()) {
            return "No marks found to delete for Roll No: " + rollno + ", Sem: " + semNo;
        }

        studentMarksRepository.delete(existing.get());
        return "Marks deleted successfully";
    }

    // ✅ Get marks and grade
    public Map<String, Object> getMarksAndGrade(String rollno, int semNo) {
        Optional<StudentMarksEntity> opt = studentMarksRepository.findByRollnoAndSemNo(rollno, semNo);
        if (opt.isEmpty()) {
            return Map.of("message", "Marks not found");
        }

        StudentMarksEntity marks = opt.get();
        Map<String, Integer> allMarks = new HashMap<>();
        if (marks.getMid1() != null) allMarks.putAll(marks.getMid1());
        if (marks.getMid2() != null) allMarks.putAll(marks.getMid2());
        if (marks.getSemMarks() != null) allMarks.putAll(marks.getSemMarks());

        double avg = allMarks.values().stream().mapToInt(Integer::intValue).average().orElse(0.0);
        String grade = calculateGrade(avg);

        return Map.of(
                "rollno", marks.getRollno(),
                "semNo", marks.getSemNo(),
                "regulation", marks.getRegulation(),
                "mid1", marks.getMid1(),
                "mid2", marks.getMid2(),
                "semMarks", marks.getSemMarks(),
                "average", avg,
                "grade", grade
        );
    }

    private String calculateGrade(double avg) {
        if (avg >= 90) return "A+";
        else if (avg >= 80) return "A";
        else if (avg >= 70) return "B+";
        else if (avg >= 60) return "B";
        else if (avg >= 50) return "C";
        else return "F";
    }
}
