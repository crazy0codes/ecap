package com.student.ecap.respository;

import com.student.ecap.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, String> {
    LoginEntity findByUsername(String username);
    boolean existsByUsername(String username);
}
