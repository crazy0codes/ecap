package com.student.ecap.respository;

import com.student.ecap.entities.FacultyDetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface FacultyDetailsRepository extends MongoRepository<FacultyDetailsEntity, String> {
    Optional<FacultyDetailsEntity> findByFacultyId(String facultyId);
}
