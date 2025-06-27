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
@CrossOrigin(origins = "*")
public class StudetailsController {

    @Autowired
    private StudetailsService studetailsService;

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@Valid @RequestBody StudetailsEntity details, BindingResult result) {
        if (result.hasErrors()) {
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
    public ResponseEntity<?> getByRollNo(@PathVariable String rollno) {
        Optional<StudetailsEntity> student = studetailsService.getByRollno(rollno);
        return student.isPresent()
                ? ResponseEntity.ok(student.get())
                : ResponseEntity.badRequest().body("Student with roll number " + rollno + " not found");
    }

    @PutMapping("/update/{rollno}")
    public ResponseEntity<String> update(@PathVariable String rollno, @RequestBody StudetailsEntity updatedDetails) {
        return ResponseEntity.ok(studetailsService.update(rollno, updatedDetails));
    }
    @DeleteMapping("/delete/{rollno}")
    public ResponseEntity<String> delete(@PathVariable String rollno) {
        return ResponseEntity.ok(studetailsService.delete(rollno));
    }
}
