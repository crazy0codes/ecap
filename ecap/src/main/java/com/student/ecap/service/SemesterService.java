package com.student.ecap.services;

import com.student.ecap.entities.SemesterEntity;
import com.student.ecap.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    public SemesterEntity saveSemester(SemesterEntity semester) {
        return semesterRepository.save(semester);
    }

    public List<SemesterEntity> getAllSemesters() {
        return semesterRepository.findAll();
    }
}
