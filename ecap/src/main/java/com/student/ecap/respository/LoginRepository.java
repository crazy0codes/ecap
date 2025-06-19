package com.student.ecap.respository;

import com.student.ecap.entities.LoginEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends MongoRepository<LoginEntity, String> {
    LoginEntity findByUsername(String username);
    boolean existsByUsername(String username);
}
