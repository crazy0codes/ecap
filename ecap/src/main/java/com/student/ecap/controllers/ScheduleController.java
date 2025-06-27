package com.student.ecap.controllers;

import com.student.ecap.entities.ScheduleEntity;
import com.student.ecap.services.ScheduleService;
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
@CrossOrigin(origins = "*")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@Valid @RequestBody ScheduleEntity schedule, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }
        return ResponseEntity.ok(scheduleService.uploadSchedule(schedule));
    }

    @GetMapping("/{semNo}")
    public ResponseEntity<?> getBySemNo(@PathVariable int semNo) {
        return scheduleService.getScheduleBySemNo(semNo)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().body("Schedule not found for SemNo: " + semNo));
    }
    @PutMapping("/update/{semNo}")
    public ResponseEntity<String> update(@PathVariable int semNo, @RequestBody ScheduleEntity schedule) {
        return ResponseEntity.ok(scheduleService.updateSchedule(semNo, schedule));
    }


    @DeleteMapping("/delete/{semNo}")
    public ResponseEntity<String> delete(@PathVariable int semNo) {
        return ResponseEntity.ok(scheduleService.deleteScheduleBySemNo(semNo));
    }
}
