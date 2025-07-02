package com.student.ecap.repository;

import com.student.ecap.model.ExamEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamEventRepository extends JpaRepository<ExamEvent, Integer> {
    // Custom query to find ExamEvent by associated ExamSchedule ID
    List<ExamEvent> findBySchedule_Id(Integer scheduleId);
}
    