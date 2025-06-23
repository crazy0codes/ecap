package com.student.ecap.controllers;
import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.services.StudetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudetailsController {

    @Autowired
    private StudetailsService studetailsService;

    @PostMapping("/upload")
    public String upload(@Valid @RequestBody StudetailsEntity details) {
        return studetailsService.upload(details);
    }

    @GetMapping("/all")
    public List<StudetailsEntity> getAll() {
        return studetailsService.getAll();
    }

    @GetMapping("/search/{rollno}")
    public Optional<StudetailsEntity> getByRollNo(@PathVariable String rollno) {
        return studetailsService.getByRollno(rollno);
    }
}
