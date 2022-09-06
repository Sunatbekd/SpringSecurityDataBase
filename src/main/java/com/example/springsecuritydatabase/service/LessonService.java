package com.example.springsecuritydatabase.service;


import com.example.springsecuritydatabase.entity.Lesson;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface LessonService {
    List<Lesson> getAllLesson();
    void addLesson(Long id,Lesson lesson);
    Lesson getLessonById(Long id);
    void updateLesson(Long id,Lesson lesson);
    void deleteLesson(Long id);
}
