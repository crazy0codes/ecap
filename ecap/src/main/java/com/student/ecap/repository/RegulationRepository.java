package com.student.ecap.repository;

import com.student.ecap.model.Regulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Regulation entity.
 * Provides standard CRUD operations for Regulation objects.
 */
@Repository // Indicates that this interface is a "Repository" component
public interface RegulationRepository extends JpaRepository<Regulation, String> {
    // JpaRepository provides methods like save(), findById(), findAll(), delete(), etc.
    // No custom methods are needed based on the current DBML.
}
