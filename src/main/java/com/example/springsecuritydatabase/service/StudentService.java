package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface StudentService {
    List<Student> getAllStudent();
    void addStudent(Long courseId,Student student);
    Student getStudentById(Long id);
    void updateStudent(Long id,Student student);
    void deleteStudent(Long id);
}
