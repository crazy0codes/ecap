package com.student.ecap.respository;

import com.student.ecap.entities.SemesterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SemesterRepository extends MongoRepository<SemesterEntity, String> {
     Optional<SemesterEntity> findBySemNo(int semNo); // assuming semNo is unique
}
