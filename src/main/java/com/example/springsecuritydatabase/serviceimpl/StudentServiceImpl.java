package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Course;
import com.example.springsecuritydatabase.entity.Student;
import com.example.springsecuritydatabase.repository.CourseRepository;
import com.example.springsecuritydatabase.repository.StudentRepository;
import com.example.springsecuritydatabase.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public void addStudent(Long id, Student student) {
        Course course = courseRepository.findCourseById(id);
        course.addStudent(student);
        student.setCourse(course);
        studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findStudentById(id);
    }

    @Override
    public void updateStudent(Long id, Student student) {
        Student student1 = studentRepository.findStudentById(id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setStudyFormat(student.getStudyFormat());
        studentRepository.save(student1);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

}
