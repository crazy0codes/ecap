package com.student.ecap.respository;

import com.student.ecap.entities.StudetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudetailsRepo extends JpaRepository<StudetailsEntity, String> {
    Optional<StudetailsEntity> findByRollno(String rollno);
}
