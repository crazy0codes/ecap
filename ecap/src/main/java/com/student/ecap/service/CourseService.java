package com.student.ecap.service;

import com.student.ecap.entity.CourseEntity;
import com.student.ecap.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public CourseEntity saveCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    public List<CourseEntity> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<CourseEntity> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public CourseEntity updateCourse(String id, CourseEntity updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setCourseName(updatedCourse.getCourseName());
            course.setDescription(updatedCourse.getDescription());
            course.setDuration(updatedCourse.getDuration());
            return courseRepository.save(course);
        }).orElse(null);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }
}
