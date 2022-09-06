package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Company;
import com.example.springsecuritydatabase.entity.Course;
import com.example.springsecuritydatabase.entity.Instructor;
import com.example.springsecuritydatabase.repository.CompanyRepository;
import com.example.springsecuritydatabase.repository.CourseRepository;
import com.example.springsecuritydatabase.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;


    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public void addCourse(Long companyId, Course course) {
        Company company = companyRepository.findCompanyById(companyId);
        company.addCourses(course);
        course.setCompany(company);
        courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findCourseById(id);
    }

    @Override
    public void updateCourse(Long id, Course course) {
    Course course1 = courseRepository.findCourseById(id);
    course1.setCourseName(course.getCourseName());
    course1.setDurationInMonth(course.getDurationInMonth());
    course1.setImage(course.getImage());
    course1.setDescription(course.getDescription());
    courseRepository.save(course1);
    }

    public void deleteCourse(Long id){
        Course course = courseRepository.findCourseById(id);
          for (Instructor i : course.getInstructors()) {
              i.setCourses(null);
          }
          course.setCompany(null);
          courseRepository.delete(course);
      }
}
