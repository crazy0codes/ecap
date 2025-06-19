package com.student.ecap.controllers;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.services.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/semesters")
public class SemesterController {

    @Autowired
    private SemesterService semesterService;

    @PostMapping("/upload")
    public SemesterEntity addSemester(@RequestBody SemesterEntity semester) {
        return semesterService.saveSemester(semester);
    }

    @GetMapping("/seminfo")
    public List<SemesterEntity> getAllSemesters() {
        return semesterService.getAllSemesters();
    }
}
