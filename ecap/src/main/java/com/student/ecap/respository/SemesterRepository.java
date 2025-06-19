package com.student.ecap.respository;

import com.student.ecap.entities.SemesterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SemesterRepository extends MongoRepository<SemesterEntity, String>
{
}
