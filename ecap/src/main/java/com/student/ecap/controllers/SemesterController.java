package com.student.ecap.controllers;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.services.SemesterServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    @Autowired
    private SemesterServices semesterServices;
    // POST /semesters/upload
    @PostMapping("/upload")
    public ResponseEntity<String> addSemester(@Valid @RequestBody SemesterEntity semester, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }

        return ResponseEntity.ok(semesterServices.saveSemester(semester));
    }
    // GET /semesters/seminfo
    @GetMapping("/seminfo")
    public List<SemesterEntity> getAllSemesters() {
        return semesterServices.getAllSemesters();
    }

    // GET /semesters/sem/{semNo}
    @GetMapping("/sem/{semNo}")
    public ResponseEntity<SemesterEntity> getBySemNo(@PathVariable int semNo) {
        return semesterServices.getBySemNo(semNo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
