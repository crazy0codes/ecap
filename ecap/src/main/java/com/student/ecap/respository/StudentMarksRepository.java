package com.student.ecap.respository;

import com.student.ecap.entities.StudentMarksEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentMarksRepository extends JpaRepository<StudentMarksEntity, Long> {
    Optional<StudentMarksEntity> findByRollnoAndSemNo(String rollno, int semNo);
}
