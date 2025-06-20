package com.student.ecap.respository;

import com.student.ecap.entities.StudentMarksEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface StudentMarksRepository extends MongoRepository<StudentMarksEntity, String>
{
    Optional<StudentMarksEntity> findByRollnoAndSemNo(String rollno, int semNo);
}

