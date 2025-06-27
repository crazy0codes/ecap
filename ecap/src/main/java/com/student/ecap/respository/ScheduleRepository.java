package com.student.ecap.respository;

import com.student.ecap.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    Optional<ScheduleEntity> findBySemNo(Integer semNo);
}
