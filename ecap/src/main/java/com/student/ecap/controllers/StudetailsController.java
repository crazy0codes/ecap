package com.student.ecap.controllers;

import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.services.StudetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudetailsController {

    @Autowired
    private StudetailsService studetailsService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@Valid @RequestBody StudetailsEntity details, BindingResult result) {
        if (result.hasErrors())
        {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }
        return ResponseEntity.ok(studetailsService.upload(details));
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
