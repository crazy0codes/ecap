package com.student.ecap.services;

import com.student.ecap.entities.AttendanceEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.student.ecap.respository.AttendanceRepository;

@Service
public class AttendanceServices {

    @Autowired
    AttendanceRepository attendanceRepository;

    public AttendanceEntity getById(String studentId){
        ObjectId id = new ObjectId(studentId);
        return attendanceRepository.getStudentById(studentId);
    }

    public AttendanceEntity update(AttendanceEntity attendance){
        return attendanceRepository.save(attendance);
    }

}
