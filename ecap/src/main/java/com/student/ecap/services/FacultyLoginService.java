package com.student.ecap.services;

import com.student.ecap.entities.FacultyEntity;
import com.student.ecap.respository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyLoginService {

    @Autowired
    private FacultyRepository facultyRepository;

    public boolean userExists(String username) {
        return facultyRepository.findByUsername(username) != null;
    }

    public boolean validateCredentials(String username, String password) {
        FacultyEntity user = facultyRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public void registerUser(String username, String password) {
        FacultyEntity user = new FacultyEntity();
        user.setUsername(username);
        user.setPassword(password);
        facultyRepository.save(user);
    }

    public boolean forceChangePassword(String username, String newPassword) {
        FacultyEntity user = facultyRepository.findByUsername(username);
        if (user != null) {
            user.setPassword(newPassword);
            facultyRepository.save(user);
            return true;
        }
        return false;
    }

    public void deleteUser(String username) {
        FacultyEntity user = facultyRepository.findByUsername(username);
        if (user != null) {
            facultyRepository.delete(user);
        }
    }

    public List<FacultyEntity> getAllUsers() {
        return facultyRepository.findAll();
    }
}