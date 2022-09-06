package com.example.springsecuritydatabase.repository;

import com.example.springsecuritydatabase.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {

    Student findStudentById(Long id);
}
