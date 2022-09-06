package com.example.springsecuritydatabase.serviceimpl;

import com.example.springsecuritydatabase.entity.Lesson;
import com.example.springsecuritydatabase.entity.Task;
import com.example.springsecuritydatabase.repository.LessonRepository;
import com.example.springsecuritydatabase.repository.TaskRepository;
import com.example.springsecuritydatabase.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonRepository lessonRepository;


    @Override
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void addTasks(Long id, Task task){
        Lesson lesson = lessonRepository.findLessonById(id);
        lesson.addTask(task);
        task.setLesson(lesson);
        taskRepository.save(task);
    }

    public Task getTaskById(Long id){
        return taskRepository.findTaskById(id);
    }

    @Override
    public void updateTask(Long taskId, Task task) {
    Task task1 = taskRepository.findTaskById(taskId);
    task1.setTaskName(task.getTaskName());
    task1.setDeadline(task.getDeadline());
    taskRepository.save(task1);
    }

    public void deleteTask(Long id){
        Task task = taskRepository.findTaskById(id);
        task.setLesson(null);
        taskRepository.delete(task);
    }

}
