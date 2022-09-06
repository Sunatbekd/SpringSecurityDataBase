package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Company;
import com.example.springsecuritydatabase.entity.Course;
import com.example.springsecuritydatabase.entity.Instructor;
import com.example.springsecuritydatabase.repository.CompanyRepository;
import com.example.springsecuritydatabase.repository.CourseRepository;
import com.example.springsecuritydatabase.repository.InstructorRepository;
import com.example.springsecuritydatabase.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {

    private final InstructorRepository instructorRepository;
    private final CompanyRepository companyRepository;
    private  final CourseRepository courseRepository;

    @Override
    public List<Instructor> getAllInstructorsByCompany() {
        return instructorRepository.findAll();
    }

    public void addInstructor(Long id, Instructor instructor){
        Company company = companyRepository.findCompanyById(id);
        company.addInstructor(instructor);
        instructor.setCompany(company);
        instructorRepository.save(instructor);
    }

    public Instructor getInstructorById(Long id){
        return instructorRepository.findInstructorById(id);
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
    Instructor instructor1 = instructorRepository.findInstructorById(id);
    instructor1.setFirstName(instructor.getFirstName());
    instructor1.setLastName(instructor.getLastName());
    instructor1.setEmail(instructor.getEmail());
    instructor1.setPhoneNumber(instructor.getPhoneNumber());
    instructor1.setSpecialization(instructor.getSpecialization());
    instructorRepository.save(instructor1);
    }

    public void deleteInstructor(Long id){
        Instructor instructor = instructorRepository.findInstructorById(id);
        for (Course c : instructor.getCourses()) {
            c.setInstructors(null);
        }
        instructorRepository.delete(instructor);
    }

    @Override
    public void assignedInstructorToCourse(Long instructorID, Long courseID) {
    Instructor instructor = instructorRepository.findInstructorById(instructorID);
    Course course = courseRepository.findCourseById(courseID);
    instructor.addCourse(course);
    course.addInstructor(instructor);
    instructorRepository.save(instructor);
    courseRepository.save(course);
    }
}
