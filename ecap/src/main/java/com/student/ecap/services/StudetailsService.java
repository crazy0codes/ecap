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
        if (studetailsRepo.findByRollno(details.getRollno()).isPresent()) {
            return "User already exists for roll number: " + details.getRollno();
        }
        studetailsRepo.save(details);
        return "Uploaded successfully";
    }

    public List<StudetailsEntity> getAll() {
        return studetailsRepo.findAll();
    }

    public Optional<StudetailsEntity> getByRollno(String rollno) {
        return studetailsRepo.findByRollno(rollno);
    }

    public String update(String rollno, StudetailsEntity updatedDetails) {
        Optional<StudetailsEntity> existing = studetailsRepo.findByRollno(rollno);
        if (existing.isEmpty()) {
            return "Student with roll number " + rollno + " not found";
        }

        StudetailsEntity student = existing.get();
        student.setName(updatedDetails.getName());
        student.setDepartment(updatedDetails.getDepartment());
        student.setYear(updatedDetails.getYear());
        student.setSection(updatedDetails.getSection());
        student.setEmail(updatedDetails.getEmail());
        student.setMobileno(updatedDetails.getMobileno());
        student.setBloodgroup(updatedDetails.getBloodgroup());
        student.setVillage(updatedDetails.getVillage());
        student.setFathername(updatedDetails.getFathername());
        student.setMothername(updatedDetails.getMothername());

        studetailsRepo.save(student);
        return "Student details updated successfully";
    }

    public String delete(String rollno) {
        Optional<StudetailsEntity> existing = studetailsRepo.findByRollno(rollno);
        if (existing.isEmpty()) {
            return "Student with roll number " + rollno + " not found";
        }
        studetailsRepo.deleteById(rollno);
        return "Student deleted successfully";
    }
}
