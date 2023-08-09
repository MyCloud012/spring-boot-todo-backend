package com.example.tasksapp.repository;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tasksapp.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
     // Add a method to search tasks by description
    List<Task> findByDescriptionContainingIgnoreCase(String searchTerm);

    // Add a method to filter tasks by completion status
    List<Task> findByComplete(boolean complete);

    // Add a method to filter tasks by due date
    List<Task> findByDueDate(LocalDate dueDate);

    // Add a method to filter tasks by due date and completion status
    List<Task> findByDueDateAndComplete(LocalDate dueDate, boolean complete);
}
