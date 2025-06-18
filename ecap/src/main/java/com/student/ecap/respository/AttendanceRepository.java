package com.student.ecap.respository;

import com.student.ecap.entities.AttendanceEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends MongoRepository<AttendanceEntity, Long> {
    AttendanceEntity getByStudentId(ObjectId studentId);

    AttendanceEntity update(AttendanceEntity attendance);
}

