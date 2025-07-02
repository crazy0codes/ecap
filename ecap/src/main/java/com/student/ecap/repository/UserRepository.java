package com.student.ecap.repository;

import com.student.ecap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> { // Primary key type is String (rollNumber)

    /**
     * Finds a User by their roll number (which acts as the username).
     * @param rollNumber The roll number to search for.
     * @return An Optional containing the User if found.
     */
    Optional<User> findByRollNumber(String rollNumber);
}
