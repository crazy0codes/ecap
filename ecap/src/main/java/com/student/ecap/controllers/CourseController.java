package com.student.ecap.controller;

import com.student.ecap.entity.CourseEntity;
import com.student.ecap.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public CourseEntity createCourse(@RequestBody CourseEntity course) {
        return courseService.saveCourse(course);
    }

    @GetMapping
    public List<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseEntity getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public CourseEntity updateCourse(@PathVariable String id, @RequestBody CourseEntity course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }
}
