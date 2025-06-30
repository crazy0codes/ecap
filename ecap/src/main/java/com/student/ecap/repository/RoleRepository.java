package com.student.ecap.repository;

import com.student.ecap.model.ERole;
import com.student.ecap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Role entity.
 * Provides standard CRUD operations and custom query methods for Role objects.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Finds a Role by its name (ERole enum).
     * @param name The name of the role (e.g., ERole.ROLE_STUDENT).
     * @return An Optional containing the found Role, or empty if not found.
     */
    Optional<Role> findByName(ERole name);
}
