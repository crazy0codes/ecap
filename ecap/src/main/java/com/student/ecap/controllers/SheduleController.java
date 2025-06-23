package com.student.ecap.controllers;

import com.student.ecap.entities.SheduleEntity;
import com.student.ecap.services.SheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/schedule")
public class SheduleController {

    @Autowired
    private SheduleService sheduleService;

    @PostMapping("/upload")
    public String upload(@Valid @RequestBody SheduleEntity shedule) {
        return sheduleService.uploadShedule(shedule);
    }

    @GetMapping("/{semNo}")
    public Optional<SheduleEntity> getBySemNo(@PathVariable int semNo) {
        return sheduleService.getSheduleBySemNo(semNo);
    }
}

