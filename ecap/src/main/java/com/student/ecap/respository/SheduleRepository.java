package com.student.ecap.respository;

import com.student.ecap.entities.SheduleEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface SheduleRepository extends MongoRepository<SheduleEntity, String> {
    Optional<SheduleEntity> findBySemNo(int semNo);
}