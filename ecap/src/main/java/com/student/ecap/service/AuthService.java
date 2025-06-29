package com.student.ecap.service;

import com.student.ecap.dto.LoginRequest;
import com.student.ecap.dto.LoginResponse;
import com.student.ecap.model.User;
import com.student.ecap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class for handling authentication logic, primarily student login.
 */
@Service // Indicates that this class is a "Service" component
public class AuthService {

    private final UserRepository userRepository;

    @Autowired // Injects UserRepository dependency
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Authenticates a student based on roll number and password.
     * In a real application, password hashing (e.g., BCrypt) would be used.
     *
     * @param loginRequest DTO containing roll number and password.
     * @return LoginResponse indicating success or failure.
     */
    public LoginResponse authenticateStudent(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findById(loginRequest.getRollNumber());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            // In a real app, compare hashed passwords: passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())
            if (user.getPassword().equals(loginRequest.getPassword())) {
                return new LoginResponse("Login successful!", true, user.getRollNumber());
            } else {
                return new LoginResponse("Invalid password.", false, null);
            }
        } else {
            return new LoginResponse("Student with this roll number not found.", false, null);
        }
    }
}
