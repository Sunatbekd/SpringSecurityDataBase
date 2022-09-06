package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Course;
import com.example.springsecuritydatabase.entity.Lesson;
import com.example.springsecuritydatabase.repository.CourseRepository;
import com.example.springsecuritydatabase.repository.LessonRepository;
import com.example.springsecuritydatabase.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;


    @Override
    public List<Lesson> getAllLesson() {
        return lessonRepository.findAll();
    }

    public void addLesson(Long id, Lesson lesson){
        Course course = courseRepository.findCourseById(id);
        course.addLesson(lesson);
        lesson.setCourse(course);
        lessonRepository.save(lesson);
    }

    public Lesson getLessonById(Long id ){
        return lessonRepository.findLessonById(id);
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
    Lesson lesson1 = lessonRepository.findLessonById(id);
    lesson1.setLessonName(lesson.getLessonName());
    lessonRepository.save(lesson1);
    }

    public void deleteLesson(Long id){
        Lesson lesson = lessonRepository.findLessonById(id);
        lesson.setCourse(null);
        lessonRepository.delete(lesson);
    }
}
