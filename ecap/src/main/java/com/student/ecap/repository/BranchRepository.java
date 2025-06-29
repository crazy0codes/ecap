package com.student.ecap.repository;

import com.student.ecap.model.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Branch entity.
 * Provides standard CRUD operations for Branch objects.
 */
@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
