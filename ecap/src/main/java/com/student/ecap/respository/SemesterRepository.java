package com.student.ecap.respository;

import com.student.ecap.entities.SemesterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SemesterRepository extends JpaRepository<SemesterEntity, Integer> {
     Optional<SemesterEntity> findBySemesterNumber(int semesterNumber);
}
