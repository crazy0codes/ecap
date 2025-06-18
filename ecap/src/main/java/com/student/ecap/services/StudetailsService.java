package com.student.ecap.services;

import com.student.ecap.entities.StudetailsEntity;
import com.student.ecap.respository.StudetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudetailsService {

    @Autowired
    private StudetailsRepo studetailsRepo;

    public String upload(StudetailsEntity details) {
        try {
            studetailsRepo.save(details);
        } catch (Exception e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
        return "Uploaded successfully";
    }

    public List<StudetailsEntity> getall() {
        return (List<StudetailsEntity>) studetailsRepo.findAll();
    }

    public StudetailsEntity getByRollno(String rollno) {
        return studetailsRepo.findByRollno(rollno);
    }

}
