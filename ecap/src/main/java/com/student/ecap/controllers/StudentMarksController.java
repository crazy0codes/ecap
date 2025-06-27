package com.student.ecap.controllers;

import com.student.ecap.entities.StudentMarksEntity;
import com.student.ecap.services.StudentMarksService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/marks")
@CrossOrigin(origins = "*")
public class StudentMarksController {

    @Autowired
    private StudentMarksService studentMarksService;

    // ✅ Insert marks
    @PostMapping("/insert")
    public ResponseEntity<String> insertMarks(@Valid @RequestBody StudentMarksEntity marks, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }
        return ResponseEntity.ok(studentMarksService.insertMarks(marks));
    }

    // ✅ Get marks and grade
    @GetMapping("/get/{rollno}/{semNo}")
    public ResponseEntity<Map<String, Object>> getMarks(@PathVariable String rollno, @PathVariable int semNo) {
        return ResponseEntity.ok(studentMarksService.getMarksAndGrade(rollno, semNo));
    }

    // ✅ Edit marks
    @PutMapping("/edit/{rollno}/{semNo}")
    public ResponseEntity<String> editMarks(@PathVariable String rollno,
                                            @PathVariable int semNo,
                                            @Valid @RequestBody StudentMarksEntity updatedMarks,
                                            BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }

        return ResponseEntity.ok(studentMarksService.updateMarks(rollno, semNo, updatedMarks));
    }

    // ✅ Delete marks
    @DeleteMapping("/delete/{rollno}/{semNo}")
    public ResponseEntity<String> deleteMarks(@PathVariable String rollno, @PathVariable int semNo) {
        return ResponseEntity.ok(studentMarksService.deleteMarks(rollno, semNo));
    }
}
