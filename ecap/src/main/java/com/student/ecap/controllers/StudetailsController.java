package com.student.ecap.controllers;

import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.services.StudetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudetailsController {
    @Autowired
    private StudetailsService StudetailsService;

    @PostMapping("/uploadDetails")
    public String upload(@RequestBody StudetailsEntity details)
    {
        StudetailsService.upload(details);
        return "upload succesfull";
    }
    @GetMapping("/showdetails")
    public List<StudetailsEntity> getall(){
        return StudetailsService.getall();
    }
    @GetMapping("/searchStu/{rollno}")
    public StudetailsEntity getByrollno(@PathVariable String rollNo) {
        return StudetailsService.getByrollno(rollNo);
    }
}
