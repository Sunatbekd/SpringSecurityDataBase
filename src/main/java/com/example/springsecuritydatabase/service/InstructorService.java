package com.example.springsecuritydatabase.service;


import com.example.springsecuritydatabase.entity.Instructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface InstructorService {
    List<Instructor> getAllInstructorsByCompany();
    void addInstructor(Long id,Instructor instructor);
    Instructor getInstructorById(Long id);
    void updateInstructor(Long id,Instructor instructor);
    void deleteInstructor(Long instructorId);
    void assignedInstructorToCourse(Long instructorID, Long courseID);
}
