package com.example.springsecuritydatabase.service;

import com.example.springsecuritydatabase.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface TaskService {
    List<Task> getAllTask();
    void addTasks(Long lessonId, Task task);
    Task getTaskById(Long id);
    void updateTask(Long taskId,Task task);
    void deleteTask( Long taskId);
}
