package com.example.springsecuritydatabase.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(generator = "company_gen",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "company_gen", sequenceName = "company_seq", allocationSize = 1)
    private Long id;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "located_country")
    private String locatedCountry;
    @OneToMany(
            cascade = CascadeType.ALL,mappedBy = "company")
    private List<Course> courses;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Student>students;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "company")
    private List<Instructor>instructors;

    public void addCourses(Course course){
        if (courses==null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    public void addInstructor(Instructor instructor){

    }
}
