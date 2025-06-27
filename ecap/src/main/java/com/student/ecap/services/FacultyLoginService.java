package com.student.ecap.services;

import com.student.ecap.entities.FacultyEntity;
import com.student.ecap.respository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyLoginService {

    @Autowired
    private FacultyRepository facultyRepo;

    public ResponseEntity<String> login(FacultyEntity loginRequest) {
        String username = loginRequest.getUsername() != null ? loginRequest.getUsername().trim() : null;
        String password = loginRequest.getPassword() != null ? loginRequest.getPassword().trim() : null;

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required and cannot be empty");
        }

        FacultyEntity user = facultyRepo.findByUsername(username);
        if (user == null) {
            return ResponseEntity.status(404).body("User does not exist");
        }

        if (!user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful");
    }

    public ResponseEntity<String> register(FacultyEntity newUser) {
        String username = newUser.getUsername() != null ? newUser.getUsername().trim() : null;
        String password = newUser.getPassword() != null ? newUser.getPassword().trim() : null;

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Username and password are required");
        }

        if (facultyRepo.existsByUsername(username)) {
            return ResponseEntity.status(409).body("User already exists");
        }

        FacultyEntity userToSave = new FacultyEntity();
        userToSave.setUsername(username);
        userToSave.setPassword(password);

        facultyRepo.save(userToSave);
        return ResponseEntity.status(201).body("User registered successfully");
    }

    public ResponseEntity<String> changePassword(String username, String newPassword) {
        FacultyEntity user = facultyRepo.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("Username not found");
        }

        user.setPassword(newPassword);
        facultyRepo.save(user);

        return ResponseEntity.ok("Password changed successfully");
    }

    public ResponseEntity<String> deleteUser(String username) {
        FacultyEntity user = facultyRepo.findByUsername(username);
        if (user == null) {
            return ResponseEntity.badRequest().body("User does not exist");
        }

        facultyRepo.delete(user);
        return ResponseEntity.ok("User deleted successfully");
    }

    public boolean userExists(String username) {
        return facultyRepo.existsByUsername(username);
    }

    public boolean validateCredentials(String username, String password) {
        FacultyEntity user = facultyRepo.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public List<FacultyEntity> getAllUsers() {
        return facultyRepo.findAll();
    }
}
