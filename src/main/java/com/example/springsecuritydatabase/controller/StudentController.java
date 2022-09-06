package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Student;
import com.example.springsecuritydatabase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping("/allStudents/{courseId}")
    public String getAllStudents(@PathVariable Long courseId, Model model){
        model.addAttribute("allStudent",studentService.getAllStudent());
        model.addAttribute("courseId",courseId);
        return "/student/students";
    }
    @GetMapping("/addStudent/{courseId}")
    public String addStudent(@PathVariable Long courseId , Model model){
        model.addAttribute("student", new Student());
        model.addAttribute("courseId",courseId);
        return "/student/addStudent";
    }
    @PostMapping("/saveStudent/{courseId}")
    public String saveStudent(@ModelAttribute ("student") Student student,@PathVariable Long courseId){
        studentService.addStudent(courseId,student);
        return "redirect:/students/allStudents/ "+courseId;
    }

    @GetMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable Long id,Model model){
        Student student = studentService.getStudentById(id);
        model.addAttribute("student",student);
        model.addAttribute("courseId",student.getCourse().getId());
        return "/student/updateStudent";
    }
    @PostMapping("/saveUpdateStudent/{courseId}/{studentId}")
    public String saveUpdateStudent(@PathVariable("studentId") Long studentId,
                                    @PathVariable("courseId") Long courseId,
                                    @ModelAttribute("student") Student student){
        studentService.updateStudent(studentId,student);
        return "redirect:/students/allStudents/ "+courseId;
    }

    //    @RequestMapping("/edit/{id}")
//    public ModelAndView editProduct(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("edit_product");
//        Product product = productService.getProductById(id);
//        modelAndView.addObject(product);
//        return modelAndView;
//    }
//    @RequestMapping("/updateStudent/{id}")
//    public ModelAndView updateStudent(@PathVariable("id")Long id){
//        ModelAndView modelAndView = new ModelAndView("/student/updateStudent");
//        Student student = studentService.getStudentById(id);
//        modelAndView.addObject(student);
//        return modelAndView;
//    }

    @RequestMapping("/deleteStudent/{courseId}/{id}")
    public String deleteStudent(@PathVariable("id") Long id,
                                @PathVariable("courseId") Long courseId){
        studentService.deleteStudent(id);
        return "redirect:/students/allStudents/ "+courseId;
    }
}
