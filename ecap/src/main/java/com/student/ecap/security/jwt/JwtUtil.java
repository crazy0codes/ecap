package com.student.ecap.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

/**
 * Utility class for JWT token generation, validation, and parsing.
 * Reads JWT secret and expiration from application.properties.
 */
@Component
public class JwtUtil {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${ecap.app.jwtSecret}") // Injected from application.properties
    private String jwtSecret;

    @Value("${ecap.app.jwtExpirationMs}") // Injected from application.properties
    private int jwtExpirationMs;

    /**
     * Generates a JWT token from user authentication details.
     * @param authentication The authenticated user object from Spring Security.
     * @return The generated JWT string.
     */
    public String generateJwtToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrincipal.getUsername())) // User's rollNumber (username)
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs)) // Token expiration time
                .signWith(key(), SignatureAlgorithm.HS512) // Sign with HMAC SHA-512 using the secret key
                .compact(); // Build the JWT
    }

    /**
     * Generates the signing key from the JWT secret.
     * @return The signing Key object.
     */
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    /**
     * Extracts the username (rollNumber) from a JWT token.
     * @param token The JWT string.
     * @return The username (subject) from the token.
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build()
                .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validates a JWT token.
     * @param authToken The JWT string to validate.
     * @return true if the token is valid, false otherwise.
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }
}
