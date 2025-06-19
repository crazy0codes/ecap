package com.student.ecap.controllers;

import com.student.ecap.entities.StudentMarksEntity;
import com.student.ecap.services.StudentMarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("marks")
public class StudentMarksController {

    @Autowired
    private StudentMarksService studentMarksService;

    // Insert new marks for a rollno & semNo (only once)
    @PostMapping("/insert")
    public String insertMarks(@RequestBody StudentMarksEntity marks) {
        return studentMarksService.insertMarks(marks);
    }

    // Fetch marks for a student rollno and semester
    @GetMapping("/get/{rollno}/{semNo}")
    public StudentMarksEntity getMarks(@PathVariable String rollno, @PathVariable int semNo) {
        return studentMarksService.getMarks(rollno, semNo).orElse(null);
    }

}
