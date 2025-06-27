package com.student.ecap.respository;

import com.student.ecap.entities.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<FacultyEntity, String> {
    FacultyEntity findByUsername(String username);
    boolean existsByUsername(String username);
}
