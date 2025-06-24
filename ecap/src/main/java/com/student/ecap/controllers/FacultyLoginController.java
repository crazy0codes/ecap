package com.student.ecap.controllers;

import com.student.ecap.entities.FacultyEntity;
import com.student.ecap.services.FacultyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/faculty")
public class FacultyLoginController {

    @Autowired
    private FacultyLoginService facultyService;

    @PostMapping("/authenticate")
    public ResponseEntity<String> login(@RequestBody FacultyEntity loginRequest) {
        return facultyService.login(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody FacultyEntity newUser) {
        return facultyService.register(newUser);
    }

    @PostMapping("/update")
    public ResponseEntity<String> changePassword(@RequestParam String username,
                                                 @RequestParam String newPassword) {
        return facultyService.changePassword(username, newPassword);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        return facultyService.deleteUser(username);
    }

    @GetMapping("/all")
    public List<FacultyEntity> getAllUsers() {
        return facultyService.getAllUsers();
    }
}
