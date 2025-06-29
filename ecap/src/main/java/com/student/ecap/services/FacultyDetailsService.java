package com.student.ecap.services;

import com.student.ecap.entities.FacultyDetailsEntity;
import com.student.ecap.respository.FacultyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyDetailsService {

    @Autowired
    private FacultyDetailsRepository facultyDetailsRepository;

    // Add new faculty details
    public String uploadFacultyDetails(FacultyDetailsEntity details) {
        if (facultyDetailsRepository.findByFacultyId(details.getFacultyId()).isPresent()) {
            return "Faculty already exists with ID: " + details.getFacultyId();
        }
        facultyDetailsRepository.save(details);
        return "Faculty details uploaded successfully";
    }

    // Get all faculty details
    public List<FacultyDetailsEntity> getAllFaculty() {
        return facultyDetailsRepository.findAll();
    }

    // Get details by faculty ID
    public Optional<FacultyDetailsEntity> getByFacultyId(String facultyId) {
        return facultyDetailsRepository.findByFacultyId(facultyId);
    }

    // Update faculty info by faculty ID
    public String updateFaculty(String facultyId, FacultyDetailsEntity updated) {
        Optional<FacultyDetailsEntity> existing = facultyDetailsRepository.findByFacultyId(facultyId);
        if (existing.isEmpty()) {
            return "Faculty with ID " + facultyId + " not found";
        }
        FacultyDetailsEntity entity = existing.get();
        entity.setName(updated.getName());
        entity.setCoursesTaught(updated.getCoursesTaught());
        entity.setBranch(updated.getBranch());
        entity.setDateOfBirth(updated.getDateOfBirth());
        facultyDetailsRepository.save(entity);
        return "Faculty details updated successfully";
    }

    // Delete faculty by faculty ID
    public String deleteFaculty(String facultyId) {
        Optional<FacultyDetailsEntity> existing = facultyDetailsRepository.findByFacultyId(facultyId);
        if (existing.isEmpty()) {
            return "Faculty with ID " + facultyId + " not found";
        }
        facultyDetailsRepository.delete(existing.get());
        return "Faculty deleted successfully";
    }
}
