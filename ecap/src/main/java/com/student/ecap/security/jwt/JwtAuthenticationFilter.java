package com.student.ecap.security.jwt;

import com.student.ecap.security.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Custom filter to intercept incoming HTTP requests and validate JWT tokens.
 * Executes once per request.
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil; // Injects JwtUtil to perform token operations

    @Autowired
    private UserDetailsServiceImpl userDetailsService; // Injects UserDetailsServiceImpl to load user details

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 1. Get JWT from the Authorization header
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtil.validateJwtToken(jwt)) {
                // 2. Extract username (rollNumber) from JWT
                String username = jwtUtil.getUserNameFromJwtToken(jwt);

                // 3. Load UserDetails for the extracted username
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                // 4. Create an Authentication object
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null, // No credentials needed as user is authenticated via token
                                userDetails.getAuthorities());

                // 5. Set authentication details (e.g., IP address, session ID)
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 6. Set the Authentication object in Spring Security Context
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Cannot set user authentication: {}", e.getMessage());
        }

        // Continue with the filter chain
        filterChain.doFilter(request, response);
    }

    /**
     * Extracts the JWT token from the Authorization header.
     * Expected format: "Bearer <JWT>"
     * @param request The HTTP request.
     * @return The JWT string, or null if not found or not in "Bearer" format.
     */
    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7); // Extract token after "Bearer "
        }

        return null;
    }
}
