package com.student.ecap.service;

import com.student.ecap.dto.LoginRequest;
import com.student.ecap.dto.LoginResponse;
import com.student.ecap.security.jwt.JwtUtil;
import com.student.ecap.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder; // New import for password encoding
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for handling authentication logic, now including JWT generation.
 */
@Service
public class AuthService {

    private final AuthenticationManager authenticationManager; // Injected AuthenticationManager
    private final JwtUtil jwtUtil; // Injected JwtUtil
    private final PasswordEncoder passwordEncoder; // Injected PasswordEncoder

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user based on roll number and password and generates a JWT.
     * The password comparison is now handled by Spring Security's AuthenticationManager.
     *
     * @param loginRequest DTO containing roll number and password.
     * @return LoginResponse with JWT and user details on success, or an error message.
     */
    public LoginResponse authenticateUser(LoginRequest loginRequest) {
        try {
            // Authenticate user using Spring Security's AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getRollNumber(), loginRequest.getPassword()));

            // Set the authenticated user in Spring Security Context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String jwt = jwtUtil.generateJwtToken(authentication);

            // Get user details from the authenticated principal
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            // Extract roles
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            // Return success response with JWT and user details
            return new LoginResponse("Login successful!", true, userDetails.getUsername(), jwt, roles);

        } catch (Exception e) {
            // Handle authentication failure (e.g., bad credentials)
            return new LoginResponse("Authentication failed: " + e.getMessage(), false, null, null, null);
        }
    }
}
