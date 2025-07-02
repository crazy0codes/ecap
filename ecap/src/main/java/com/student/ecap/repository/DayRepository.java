package com.student.ecap.repository;

import com.student.ecap.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Day entity.
 */
@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {
    // Find a Day by its name (e.g., "Monday")
    Day findByName(String name);
}
    