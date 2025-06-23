package com.student.ecap.controllers;

import com.student.ecap.entities.SheduleEntity;
import com.student.ecap.services.SheduleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class SheduleController {

    @Autowired
    private SheduleService sheduleService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@Valid @RequestBody SheduleEntity shedule, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }

        return ResponseEntity.ok(sheduleService.uploadShedule(shedule));
    }

    @GetMapping("/{semNo}")
    public Optional<SheduleEntity> getBySemNo(@PathVariable int semNo) {
        return sheduleService.getSheduleBySemNo(semNo);
    }
}
