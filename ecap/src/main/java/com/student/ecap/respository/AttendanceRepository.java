package com.student.ecap.respository;

import com.student.ecap.entities.AttendanceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends MongoRepository<AttendanceEntity, Long> {

    @Query("{ 'userId' : ?0 }")
    AttendanceEntity getStudentById(String studentId);

}

