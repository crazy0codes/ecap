package com.student.ecap.services;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.respository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterServices {

    @Autowired
    private SemesterRepository semesterRepository;

    public SemesterEntity saveSemester(SemesterEntity semester) {
        return semesterRepository.save(semester);
    }

    public List<SemesterEntity> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Optional<SemesterEntity> getBySemNo(int semNo) {
        return semesterRepository.findBySemNo(semNo);
    }
}
