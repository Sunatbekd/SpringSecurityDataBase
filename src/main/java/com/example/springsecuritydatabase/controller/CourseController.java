package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Course;
import com.example.springsecuritydatabase.entity.Instructor;
import com.example.springsecuritydatabase.service.CompanyService;
import com.example.springsecuritydatabase.service.CourseService;
import com.example.springsecuritydatabase.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;
    private final CompanyService companyService;
    private final InstructorService instructorService;

    @Autowired
    public CourseController(CourseService courseService, CompanyService companyService, InstructorService instructorService) {
        this.courseService = courseService;
        this.companyService = companyService;
        this.instructorService = instructorService;
    }

    @GetMapping("/getCourses/{id}")
    public String getAllCourses(Model model,
                                @PathVariable Long id,
                                @ModelAttribute("inst") Instructor instructor) {
        model.addAttribute("allCourse", courseService.getAllCourse());
        model.addAttribute("companyId",id);
        model.addAttribute("instructors",instructorService.getAllInstructorsByCompany());
        return "course/courses";
    }

    @GetMapping("/{id}/addCourse")
    public String addCourse(@PathVariable Long id, Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("companyId", id);
        return "course/addCourse";
    }

    @PostMapping("/{id}/saveCourse")
    public String saveCourse(@ModelAttribute("course") Course course, @PathVariable Long id) {
        courseService.addCourse(id,course);
        return "redirect:/courses/getCourses/ " + id;
    }

    @GetMapping("/UpdateCourse/{courseId}")
    public String updateCourse(@PathVariable("courseId") long id, Model model) {
        Course course = courseService.getCourseById(id);
        model.addAttribute("course",course);
        model.addAttribute("companyId", course.getCompany().getId());
        return "course/updateCourse";
    }

    @PostMapping("{id}/{courseId}/saveUpdateCourse")
    public String saveUpdateCourse(@PathVariable("courseId") Long courseId,
                                   @PathVariable ("id") Long id,
                                   @ModelAttribute("course") Course course) {
        courseService.updateCourse(courseId, course);
        return "redirect:/courses/getCourses/ "+id;
    }

//    @RequestMapping("/updateCourse/{id}")
//    public ModelAndView updateCourse(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("/course/updateCourse");
//        Course course = courseService.getCourseById(id);
//        modelAndView.addObject(course);
//        return modelAndView;
//    }


    //    @RequestMapping("/edit/{id}")
//    public ModelAndView editProduct(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("edit_product");
//        Product product = productService.getProductById(id);
//        modelAndView.addObject(product);
//        return modelAndView;
//    }

    @RequestMapping("/{courseId}/{companyId}")
    public String deleteCourse(@PathVariable("courseId") long id,
                               @PathVariable("companyId") Long companyId) {
        courseService.deleteCourse(id);
        return "redirect:/courses/getCourses/ " + companyId;
    }

    @PostMapping("/saveAssignInstructorToCourse/{companyId}/{courseId}")
    private String saveAssign(@PathVariable("courseId")Long courseId,
                              @ModelAttribute("inst") Instructor instructor,
                              @PathVariable("companyId") Long compId) {
        instructorService.assignedInstructorToCourse(instructor.getId(),courseId);
        return "redirect:/courses/getCourses/ "+compId;
    }
}
