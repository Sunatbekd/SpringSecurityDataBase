package com.example.springsecuritydatabase.repository;

import com.example.springsecuritydatabase.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson,Long> {

    Lesson findLessonById(Long id);
}
