package com.student.ecap.repository;

import com.student.ecap.model.ERole;
import com.student.ecap.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    /**
     * Finds a Role entity by its name (ERole enum value).
     * @param name The ERole enum value (e.g., ERole.ROLE_STUDENT).
     * @return An Optional containing the Role if found.
     */
    Optional<Role> findByName(ERole name);
}
