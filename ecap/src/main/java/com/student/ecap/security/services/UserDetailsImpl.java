package com.student.ecap.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.student.ecap.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Custom implementation of Spring Security's UserDetails interface.
 * Wraps our application's User entity to be compatible with Spring Security's authentication process.
 */
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String rollNumber; // This serves as the username in Spring Security context

    @JsonIgnore // Prevents password from being serialized into JSON response
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String rollNumber, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.rollNumber = rollNumber;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     * Builds a UserDetailsImpl object from our custom User entity.
     * @param user The application's User entity.
     * @return A UserDetailsImpl instance.
     */
    public static UserDetailsImpl build(User user) {
        // Convert application's Role objects to Spring Security's GrantedAuthority objects
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name())) // Convert ERole enum to String (e.g., "ROLE_STUDENT")
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getRollNumber(),
                user.getPassword(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return rollNumber; // Our rollNumber acts as the username for authentication
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Simple implementation: account never expires
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Simple implementation: account never locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Simple implementation: credentials never expire
    }

    @Override
    public boolean isEnabled() {
        return true; // Simple implementation: account is always enabled
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(rollNumber, user.rollNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber);
    }
}
