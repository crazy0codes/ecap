package com.student.ecap.respository;

import com.student.ecap.entities.FacultyDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacultyDetailsRepository extends JpaRepository<FacultyDetailsEntity, Long> {
    Optional<FacultyDetailsEntity> findByFacultyId(String facultyId);
    void deleteByFacultyId(String facultyId);
}
