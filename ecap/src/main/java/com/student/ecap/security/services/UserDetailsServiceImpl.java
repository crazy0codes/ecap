package com.student.ecap.security.services; // Corrected package

import com.student.ecap.model.User; // Corrected import for User model
import com.student.ecap.repository.UserRepository; // Corrected import for UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * Responsible for loading user-specific data during authentication.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    /**
     * Locates the user based on the username (rollNumber).
     * @param rollNumber The roll number (username) identifying the user whose data is required.
     * @return A UserDetails object for the given user.
     * @throws UsernameNotFoundException If the user cannot be found.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String rollNumber) throws UsernameNotFoundException {
        // Find the user by rollNumber
        User user = userRepository.findByRollNumber(rollNumber) // Using findByRollNumber from UserRepository
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with roll number: " + rollNumber));

        // Build and return a Spring Security UserDetails object from your custom User entity
        // This UserDetails object contains the username, password, and authorities (roles).
        return UserDetailsImpl.build(user);
    }
}