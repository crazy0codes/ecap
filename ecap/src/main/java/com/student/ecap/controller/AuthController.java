package com.student.ecap.controller;

import com.student.ecap.dto.LoginRequest;
import com.student.ecap.dto.LoginResponse;
import com.student.ecap.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for authentication related endpoints (e.g., user login).
 */
@RestController
@RequestMapping("/api/auth") // Base path for authentication
@CrossOrigin(origins = "http://localhost:5173") // Allow CORS from your frontend
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Endpoint for user login. Authenticates credentials and returns a JWT.
     * @param loginRequest DTO containing username (rollNumber) and password.
     * @return ResponseEntity with LoginResponse containing JWT on success.
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authService.authenticateUser(loginRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response); // HTTP 200 OK
        } else {
            return ResponseEntity.status(401).body(response); // HTTP 401 Unauthorized
        }
    }
}
