package com.student.ecap.repository;

import com.student.ecap.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity.
 * Provides standard CRUD operations for User objects.
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // As User's primary key is also its foreign key to Student (rollNumber),
    // the methods will use the rollNumber for identification.
}
