package com.student.ecap.respository;

import com.student.ecap.entities.StudetailsEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudetailsRepo extends MongoRepository<StudetailsEntity, String> {

    StudetailsEntity findByRollno(String rollno);

}
