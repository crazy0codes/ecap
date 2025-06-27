package com.student.ecap.services;

import com.student.ecap.entities.ScheduleEntity;
import com.student.ecap.respository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public String uploadSchedule(ScheduleEntity schedule) {
        if (scheduleRepository.findBySemNo(schedule.getSemNo()).isPresent()) {
            return "Schedule already exists for SemNo: " + schedule.getSemNo();
        }
        scheduleRepository.save(schedule);
        return "Schedule uploaded successfully.";
    }

    public Optional<ScheduleEntity> getScheduleBySemNo(int semNo) {
        return scheduleRepository.findBySemNo(semNo);
    }

    public String updateSchedule(int semNo, ScheduleEntity updatedSchedule) {
        Optional<ScheduleEntity> existing = scheduleRepository.findBySemNo(semNo);
        if (existing.isEmpty()) {
            return "Schedule not found for SemNo: " + semNo;
        }

        ScheduleEntity schedule = existing.get();
        schedule.setItems(updatedSchedule.getItems());
        scheduleRepository.save(schedule);
        return "Schedule updated successfully.";
    }

    public String deleteScheduleBySemNo(int semNo) {
        Optional<ScheduleEntity> schedule = scheduleRepository.findBySemNo(semNo);
        if (schedule.isPresent()) {
            scheduleRepository.delete(schedule.get());
            return "Schedule deleted for SemNo: " + semNo;
        } else {
            return "No schedule found for SemNo: " + semNo;
        }
    }
}
