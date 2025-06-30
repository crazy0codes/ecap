package com.student.ecap.security;

import com.student.ecap.security.jwt.AuthEntryPointJwt;
import com.student.ecap.security.jwt.JwtAuthenticationFilter;
import com.student.ecap.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security configuration class.
 * Configures authentication, authorization, password encoding, and JWT filter.
 */
@Configuration // Marks this as a Spring configuration class
@EnableMethodSecurity(prePostEnabled = true) // Enables method-level security (e.g., @PreAuthorize)
public class SecurityConfig {

    @Autowired
    UserDetailsServiceImpl userDetailsService; // Injects our custom UserDetailsService

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler; // Injects our custom Unauthorized entry point

    /**
     * Creates and configures the JWT Authentication Filter.
     * @return An instance of JwtAuthenticationFilter.
     */
    @Bean
    public JwtAuthenticationFilter authenticationJwtTokenFilter() {
        return new JwtAuthenticationFilter();
    }

    /**
     * Configures the DaoAuthenticationProvider, which uses UserDetailsService
     * and PasswordEncoder to authenticate users.
     * @param passwordEncoder The PasswordEncoder bean.
     * @return Configured DaoAuthenticationProvider.
     */
    @Bean
    public DaoAuthenticationProvider authenticationProvider(PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Set our custom UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder); // Set the password encoder
        return authProvider;
    }

    /**
     * Exposes the AuthenticationManager bean, which is used to authenticate users.
     * @param authConfig The AuthenticationConfiguration.
     * @return The AuthenticationManager.
     * @throws Exception If an error occurs while getting the AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Defines the PasswordEncoder to be used for hashing passwords.
     * BCryptPasswordEncoder is highly recommended for strong password hashing.
     * @return An instance of BCryptPasswordEncoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures the security filter chain. This is the core of Spring Security setup.
     * @param http The HttpSecurity object to configure.
     * @return The built SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable) // Disable CSRF for stateless APIs (JWT)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler)) // Handle unauthorized access
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Configure stateless sessions (no session cookies)
                .authorizeHttpRequests(auth -> auth
                        // Allow unauthenticated access to authentication endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        // Allow all requests to upload endpoints for now (will be restricted by @PreAuthorize later)
                        .requestMatchers("/api/upload/**").permitAll() // TEMPORARY: These will be secured with roles later
                        // All other requests require authentication
                        .anyRequest().authenticated()
                );

        // Add our custom JWT authentication filter before Spring Security's UsernamePasswordAuthenticationFilter
        http.authenticationProvider(authenticationProvider(passwordEncoder()));
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
