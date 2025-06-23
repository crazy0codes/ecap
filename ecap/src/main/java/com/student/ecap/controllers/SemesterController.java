package com.student.ecap.controllers;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.services.SemesterServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    @Autowired
    private SemesterServices semesterServices;

    // POST /semesters/upload
    @PostMapping("/upload")
    public String addSemester(@Valid @RequestBody SemesterEntity semester) {
        return semesterServices.saveSemester(semester);
    }

    // GET /semesters/seminfo
    @GetMapping("/seminfo")
    public List<SemesterEntity> getAllSemesters() {
        return semesterServices.getAllSemesters();
    }

    // GET /semesters/sem/{semNo}
    @GetMapping("/sem/{semNo}")
    public SemesterEntity getBySemNo(@PathVariable int semNo) {
        return semesterServices.getBySemNo(semNo).orElse(null);
    }
}
