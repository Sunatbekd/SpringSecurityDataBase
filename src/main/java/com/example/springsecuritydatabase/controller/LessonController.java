package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Lesson;
import com.example.springsecuritydatabase.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("lessons")
public class LessonController {

    private final LessonService lessonService;

    @Autowired
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping("/getAllLessons/{id}")
    public String getAllLessons(@PathVariable Long id, Model model) {
        model.addAttribute("allLessons", lessonService.getAllLesson());
        model.addAttribute("courseId", id);
        return "lesson/lessons";
    }

    @GetMapping("/addLesson/{courseId}")
    public String addLesson(@PathVariable Long courseId, Model model) {
        model.addAttribute("lesson", new Lesson());
        model.addAttribute("courseId", courseId);
        return "lesson/addLesson";
    }

    @PostMapping("/saveLesson/{courseId}")
    public String saveLesson(@PathVariable("courseId") Long courseId,
                             @ModelAttribute("lesson") Lesson lesson) {
        lessonService.addLesson(courseId, lesson);
        return "redirect:/lessons/getAllLessons/{courseId}";
    }

    @GetMapping("/updateLesson/{id}")
    public String UpdateLesson(@PathVariable("id") Long id, Model model) {
        Lesson lesson = lessonService.getLessonById(id);
        model.addAttribute("lesson", lesson);
        model.addAttribute("courseId", lesson.getCourse().getId());
        return "/lesson/updateLesson";
    }

    @PostMapping("saveUpdateLesson/{courseId}/{lessonId}")
    public String saveUpdateLesson(@PathVariable("courseId") Long courseId,
                                   @PathVariable("lessonId") Long lessonId,
                                   @ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(lessonId, lesson);
        return "redirect:/lessons/getAllLessons/" + courseId;
    }

    //    @RequestMapping("/edit/{id}")
//    public ModelAndView editProduct(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("edit_product");
//        Product product = productService.getProductById(id);
//        modelAndView.addObject(product);
//        return modelAndView;
//    }

//    public ModelAndView updateLesson(@PathVariable("id")Long id){
//        ModelAndView modelAndView = new ModelAndView("/lesson/updateLesson");
//        Lesson lesson = lessonService.getLessonById(id);
//        modelAndView.addObject(lesson);
//        return modelAndView;
//    }

    @RequestMapping("/deleteLesson/{courseId}/{lessonId}")
    private String deleteLesson(@PathVariable("courseId")Long courseId,
                                @PathVariable("lessonId")Long lessonId) {
        lessonService.deleteLesson(lessonId);
        return "redirect:/lessons/getAllLessons/" + courseId;
    }
}
