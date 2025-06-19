package com.student.ecap.services;

import com.student.ecap.entities.LoginEntity;
import com.student.ecap.respository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    public boolean userExists(String username) {
        return loginRepository.existsByUsername(username);
    }

    public boolean validateCredentials(String username, String password) {
        LoginEntity user = loginRepository.findByUsername(username);
        return user != null && user.getPassword().equals(password);
    }

    public void registerUser(String username, String password) {
        LoginEntity newUser = new LoginEntity(username, password);
        loginRepository.save(newUser);
    }

    public boolean forceChangePassword(String username, String newPassword) {
        LoginEntity user = loginRepository.findByUsername(username);
        if (user == null) return false;

        user.setPassword(newPassword);
        loginRepository.save(user);
        return true;
    }

    public List<LoginEntity> getAllUsers() {
        return loginRepository.findAll();
    }

    public boolean deleteUser(String username) {
        if (!loginRepository.existsByUsername(username)) {
            return false;
        }
        loginRepository.deleteById(username);
        return true;
    }
}
