package com.example.tasksapp.service;

import java.time.LocalDate;
import java.util.List;

import com.example.tasksapp.entity.Task;

public interface TaskService {
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    List<Task> searchTasks(String searchTerm);
    List<Task> filterTasksByDueDate(LocalDate dueDate);
    List<Task> filterTasksByCompletionStatus(boolean complete);
    List<Task> filterTasksByDueDateAndCompletionStatus(LocalDate dueDate, boolean complete);
    Task createTask(Task task);
    void deleteTask(Long id);
    Task updateTaskStatus(Long id, boolean isComplete);
}