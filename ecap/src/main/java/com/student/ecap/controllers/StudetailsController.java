package com.student.ecap.controllers;

import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.services.StudetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudetailsController {

    @Autowired
    private StudetailsService studetailsService;

    @PostMapping("/upload")
    public String upload(@RequestBody StudetailsEntity details) {
        studetailsService.upload(details);
        return "Upload successful";
    }

    @GetMapping("/all")
    public List<StudetailsEntity> getAll() {
        return studetailsService.getAll();
    }

    @GetMapping("/search/{rollno}")
    public StudetailsEntity getByRollNo(@PathVariable String rollno) {
        return studetailsService.getByRollno(rollno);
    }
}
