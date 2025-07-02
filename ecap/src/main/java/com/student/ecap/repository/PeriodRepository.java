package com.student.ecap.repository;

import com.student.ecap.model.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Period entity.
 */
@Repository
public interface PeriodRepository extends JpaRepository<Period, Integer> {
    // Find a Period by its number
    Period findByPeriodNumber(Integer periodNumber);
}
    