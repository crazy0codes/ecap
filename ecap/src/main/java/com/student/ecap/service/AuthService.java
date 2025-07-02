package com.student.ecap.service;

import com.student.ecap.dto.LoginRequest;
import com.student.ecap.dto.LoginResponse;
import com.student.ecap.model.User;
import com.student.ecap.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponse authenticateStudent(LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findById(loginRequest.getRollNumber());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                return new LoginResponse("Login successful!", true, user.getRollNumber());
            } else {
                return new LoginResponse("Invalid password.", false, null);
            }
        } else {
            return new LoginResponse("Student with this roll number not found.", false, null);
        }
    }
}
