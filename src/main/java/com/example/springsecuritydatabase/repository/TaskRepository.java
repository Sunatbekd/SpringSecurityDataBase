package com.example.springsecuritydatabase.repository;

import com.example.springsecuritydatabase.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

    Task findTaskById(Long id);
}
