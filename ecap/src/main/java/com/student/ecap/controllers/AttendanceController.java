package com.student.ecap.controllers;

import com.student.ecap.entities.AttendanceEntity;
import com.student.ecap.services.AttendanceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {

    @Autowired
    AttendanceServices attendanceService;

    @GetMapping("/{studentId}")
    public AttendanceEntity getById(@PathVariable String studentId){
        return attendanceService.getById(studentId);
    }

    @PostMapping("/upload")
    public AttendanceEntity update(@RequestBody AttendanceEntity attendance){
        return attendanceService.update(attendance);
    }
}
