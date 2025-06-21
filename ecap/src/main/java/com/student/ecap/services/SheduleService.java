package com.student.ecap.services;

import com.student.ecap.entities.SheduleEntity;
import com.student.ecap.respository.SheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SheduleService {

    @Autowired
    private SheduleRepository scheduleRepository;

    public String uploadShedule(SheduleEntity schedule) {
        if (scheduleRepository.findBySemNo(schedule.getSemNo()).isPresent()) {
            return "Schedule already exists for SemNo: " + schedule.getSemNo();
        }
        scheduleRepository.save(schedule);
        return "Schedule uploaded successfully.";
    }

    public Optional<SheduleEntity> getSheduleBySemNo(int semNo) {
        return scheduleRepository.findBySemNo(semNo);
    }

}