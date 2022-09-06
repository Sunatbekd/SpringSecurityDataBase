package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Instructor;
import com.example.springsecuritydatabase.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/instructors")
public class InstructorController {
    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/getAllInstructors/{courseId}")
    public String getAllInstructors(@PathVariable Long courseId, Model model) {
        List<Instructor> allInstructor = instructorService.getAllInstructorsByCompany();
        model.addAttribute("allInstructors", allInstructor);
        model.addAttribute("courseId", courseId);
        return "/instructor";
    }

    @GetMapping("/addInstructor/{courseId}")
    public String addInstructor(@PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("instructor", new Instructor());
        model.addAttribute("companyId", courseId);
        System.out.println("this method is working");
        return "/instructor/addInstructor";
    }

    @PostMapping("/saveInstructor/{id}")
    public String saveInstructor(@PathVariable Long id,
                                 @ModelAttribute("instructor") Instructor instructor) {
        instructorService.addInstructor(id, instructor);
        return "redirect:/courses/getCourses/"+id;
    }

    @GetMapping("/updateInstructor/{id}")
    public String updateInstructor(@PathVariable Long id, Model model) {
        Instructor instructor = instructorService.getInstructorById(id);
        model.addAttribute("instructor", instructor);
        model.addAttribute("companyId", instructor.getCompany().getId());
        return "/instructor/updateInstructor";
    }

    @PostMapping("/saveUpdateInstructor/{id}/{instructorId}")
    public String saveUpdateIns(@PathVariable("instructorId") Long instructorId,
                                @PathVariable("id") Long id,
                                @ModelAttribute("instructor") Instructor instructor) {
        instructorService.updateInstructor(instructorId, instructor);
        return "redirect:/courses/getCourses/"+id;
    }

    @RequestMapping("{insId}/{companyId}")
    public String deleteInstructor(@PathVariable("insId") Long id,
                                   @PathVariable Long companyId) {
        instructorService.deleteInstructor(id);
        return "redirect:/courses/getCourses/"+companyId;
    }
}
