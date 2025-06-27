package com.student.ecap.controllers;

import com.student.ecap.entities.FacultyDetailsEntity;
import com.student.ecap.services.FacultyDetailsService;
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
@RequestMapping("/faculty-details")
@CrossOrigin(origins = "*")
public class FacultyDetailsController {

    @Autowired
    private FacultyDetailsService facultyDetailsService;

    // POST /faculty-details/upload
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFaculty(@Valid @RequestBody FacultyDetailsEntity details, BindingResult result) {
        if (result.hasErrors()) {
            String errors = result.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));
            return ResponseEntity.badRequest().body("Validation Error: " + errors);
        }
        return ResponseEntity.ok(facultyDetailsService.uploadFacultyDetails(details));
    }

    // GET /faculty-details/all
    @GetMapping("/all")
    public List<FacultyDetailsEntity> getAllFaculty() {
        return facultyDetailsService.getAllFaculty();
    }

    // GET /faculty-details/{facultyId}
    @GetMapping("/{facultyId}")
    public ResponseEntity<?> getFacultyById(@PathVariable String facultyId) {
        Optional<FacultyDetailsEntity> result = facultyDetailsService.getByFacultyId(facultyId);
        return result.isPresent()
                ? ResponseEntity.ok(result.get())
                : ResponseEntity.badRequest().body("Faculty with ID " + facultyId + " not found");
    }

    // PUT /faculty-details/update/{facultyId}
    @PutMapping("/update/{facultyId}")
    public ResponseEntity<String> updateFaculty(@PathVariable String facultyId,
                                                @RequestBody FacultyDetailsEntity updated) {
        return ResponseEntity.ok(facultyDetailsService.updateFaculty(facultyId, updated));
    }
    // DELETE /faculty-details/delete/{facultyId}
    @DeleteMapping("/delete/{facultyId}")
    public ResponseEntity<String> deleteFaculty(@PathVariable String facultyId) {
        return ResponseEntity.ok(facultyDetailsService.deleteFaculty(facultyId));
    }
}
