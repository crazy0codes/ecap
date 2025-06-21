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
        if (loginRequest == null ||
                loginRequest.getUsername() == null || loginRequest.getUsername().trim().isEmpty() ||
                loginRequest.getPassword() == null || loginRequest.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required and cannot be empty");
        }

        if (!facultyService.userExists(loginRequest.getUsername())) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        boolean valid = facultyService.validateCredentials(loginRequest.getUsername(), loginRequest.getPassword());
        return valid
                ? ResponseEntity.ok("Login successful")
                : ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody FacultyEntity newUser) {
        if (newUser == null ||
                newUser.getUsername() == null || newUser.getUsername().trim().isEmpty() ||
                newUser.getPassword() == null || newUser.getPassword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        if (facultyService.userExists(newUser.getUsername())) {
            return ResponseEntity.badRequest().body("User already exists");
        }

        facultyService.registerUser(newUser.getUsername(), newUser.getPassword());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/update")
    public ResponseEntity<String> changePassword(@RequestParam String username,
                                                 @RequestParam String newPassword) {
        if (username == null || username.trim().isEmpty() ||
                newPassword == null || newPassword.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username and new password are required");
        }

        boolean changed = facultyService.forceChangePassword(username, newPassword);
        return changed
                ? ResponseEntity.ok("Password changed successfully")
                : ResponseEntity.badRequest().body("Username not found");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String username) {
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Username is required");
        }

        if (!facultyService.userExists(username)) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        facultyService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }

    @GetMapping("/all")
    public List<FacultyEntity> getAllUsers() {
        return facultyService.getAllUsers();
    }
}