package com.example.tasksapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tasksapp.entity.Task;
import com.example.tasksapp.exception.TaskNotFoundException;
import com.example.tasksapp.repository.TaskRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTaskStatus(Long id, boolean isComplete) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            task.setComplete(isComplete);
            return taskRepository.save(task);
        } else {
            throw new TaskNotFoundException("Task with id " + id + " not found.");
        }
    }

    
    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task with id " + id + " not found"));
    }

       @Override
    public List<Task> filterTasksByDueDate(LocalDate dueDate) {
        return taskRepository.findByDueDate(dueDate);
    }

    @Override
    public List<Task> filterTasksByDueDateAndCompletionStatus(LocalDate dueDate, boolean complete) {
        return taskRepository.findByDueDateAndComplete(dueDate, complete);
    }

    @Override
    public List<Task> filterTasksByCompletionStatus(boolean complete) {
        return taskRepository.findByComplete(complete);
    }

    @Override
    public List<Task> searchTasks(String searchTerm) {
        return taskRepository.findByDescriptionContainingIgnoreCase(searchTerm);
    }
}


