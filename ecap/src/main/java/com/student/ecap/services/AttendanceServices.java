package com.student.ecap.services;

import com.student.ecap.entities.AttendanceEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceServices {

    @Autowired
    AttendanceRespository attendanceRespository;

    public AttendanceEntity getById(String studentId){
        ObjectId id = new ObjectId(studentId);
        return attendanceRespository.getByStudentId(id);
    }

    public AttendanceEntity update(AttendanceEntity attendance){
        return attendanceRespository.update(attendance);
    }
}
