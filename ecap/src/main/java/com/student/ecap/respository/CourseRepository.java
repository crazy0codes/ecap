package com.student.ecap.repository;

import com.student.ecap.entity.CourseEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseRepository extends MongoRepository<CourseEntity, String> {
}
