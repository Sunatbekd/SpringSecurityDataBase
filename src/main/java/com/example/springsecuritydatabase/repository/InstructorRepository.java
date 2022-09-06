package com.example.springsecuritydatabase.repository;

import com.example.springsecuritydatabase.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
    Instructor findInstructorById(Long id);
}
