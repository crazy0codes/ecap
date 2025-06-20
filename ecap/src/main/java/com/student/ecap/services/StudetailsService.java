package com.student.ecap.services;

import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.respository.StudetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudetailsService {

    @Autowired
    private StudetailsRepo studetailsRepo;

    public String upload(StudetailsEntity details) {
        try {
            studetailsRepo.save(details);
            return "Uploaded successfully";
        } catch (Exception e) {
            System.out.println("Error saving student: " + e.getMessage());
            return "Upload failed";
        }
    }

    public List<StudetailsEntity> getAll() {
        return studetailsRepo.findAll();
    }

    public Optional<StudetailsEntity> getByRollno(String rollno) {
        return studetailsRepo.findByRollno(rollno);
    }
}
