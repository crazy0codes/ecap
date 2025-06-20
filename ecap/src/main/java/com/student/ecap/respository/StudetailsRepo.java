package com.student.ecap.respository;

import com.student.ecap.entities.StudetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudetailsRepo extends MongoRepository<StudetailsEntity, String> {

    Optional<StudetailsEntity> findByRollno(String rollno);

}
