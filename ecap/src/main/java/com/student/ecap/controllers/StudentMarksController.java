package com.student.ecap.controllers;

import com.student.ecap.entities.StudentMarksEntity;
import com.student.ecap.services.StudentMarksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.student.ecap.entities.SemesterEntity;
import java.util.*;

@RestController
@RequestMapping("/marks")
public class StudentMarksController {

    @Autowired
    private StudentMarksService studentMarksService;

    // ✅ Insert marks
    @PostMapping("/insert")
    public String insertMarks(@Valid @RequestBody StudentMarksEntity marks) {
        return studentMarksService.insertMarks(marks);
    }

    // ✅ Get marks + grade
    @GetMapping("/get/{rollno}/{semNo}")
    public Map<String, Object> getMarks(@PathVariable String rollno, @PathVariable int semNo) {
        return studentMarksService.getMarksAndGrade(rollno, semNo);
    }
}
