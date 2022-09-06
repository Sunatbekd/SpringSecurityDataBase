package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.entity.Course;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CourseService {
    List<Course> getAllCourse();
    void addCourse(Long companyId, Course course);
    Course getCourseById(Long id);
    void updateCourse(Long id,Course course);
    void deleteCourse(Long id);
}
