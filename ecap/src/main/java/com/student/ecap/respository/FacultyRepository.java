package com.student.ecap.respository;

import com.student.ecap.entities.FacultyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FacultyRepository extends MongoRepository<FacultyEntity, String> {
    FacultyEntity findByUsername(String username);
}