package com.example.springsecuritydatabase.controller;

import com.example.springsecuritydatabase.entity.Task;
import com.example.springsecuritydatabase.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks/{id}")
    public String getAllTasks(@PathVariable("id") Long id, Model model) {
        model.addAttribute("allTasks", taskService.getAllTask());
        model.addAttribute("id", id);
        return "task/tasks";
    }

    @GetMapping("/addTask/{id}")
    public String addTask(@PathVariable("id") Long lessonId, Model model) {
        model.addAttribute("task", new Task());
        model.addAttribute("id", lessonId);
        return "task/addTask";
    }

    @PostMapping("/saveTask/{lessonId}")
    public String saveTask(@PathVariable("lessonId") Long lessonId,
                           @ModelAttribute("task") Task task) {
        taskService.addTasks(lessonId, task);
        return "redirect:/tasks/getAllTasks/ " + lessonId;
    }

    @GetMapping("/updateTask/{taskId}")
    public String updateTask(@PathVariable("taskId") Long taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute("task", task);
        model.addAttribute("lessonId", task.getLesson().getId());
        return "/task/updateTask";
    }

    @PostMapping("/saveUpdateTask/{lessonId}/{taskId}")
    public String saveUpdateTask(@PathVariable("lessonId") Long lessonId,
                                 @PathVariable("taskId") Long taskId,
                                 @ModelAttribute("task") Task task) {
        taskService.updateTask(taskId,task);
        return "redirect:/tasks/getAllTasks/"+lessonId;
    }

    //    @RequestMapping("/edit/{id}")
//    public ModelAndView editProduct(@PathVariable("id") Long id){
//        ModelAndView modelAndView = new ModelAndView("edit_product");
//        Product product = productService.getProductById(id);
//        modelAndView.addObject(product);
//        return modelAndView;
//    }
//    @RequestMapping("/updateTask/{id}")
//    public ModelAndView updeteTask(@PathVariable("id")Long id){
//        ModelAndView modelAndView = new ModelAndView("/task/updateTask");
//        Task task = taskService.getTaskById(id);
//        modelAndView.addObject(task);
//        return modelAndView;
//    }

    @RequestMapping("/deleteTask/{lessonId}/{taskId}")
    public String deleteTask(@PathVariable("lessonId") Long lessonId,
                             @PathVariable("taskId") Long taskId) {
        taskService.deleteTask(taskId);
        return "redirect:/tasks/getAllTasks/" + lessonId;
    }

}
