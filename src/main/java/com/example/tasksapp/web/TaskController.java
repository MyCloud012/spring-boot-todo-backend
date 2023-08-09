package com.example.tasksapp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.tasksapp.entity.Task;
import com.example.tasksapp.service.TaskService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tasks")
@CrossOrigin


public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id, @RequestParam boolean complete) {
        Task updatedTask = taskService.updateTaskStatus(id, complete);
        if (updatedTask != null) {
            return ResponseEntity.ok(updatedTask);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Task>> searchTasks(@RequestParam String searchTerm) {
        List<Task> tasks = taskService.searchTasks(searchTerm);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{taskId}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long taskId) {
        Task task = taskService.getTaskById(taskId);
        if (task != null) {
            return ResponseEntity.ok(task);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



@GetMapping("/filter")
public ResponseEntity<List<Task>> filterTasks(
        @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dueDate,
        @RequestParam(required = false) Optional<Boolean> complete
) {
    if (dueDate != null && complete.isPresent()) {
        List<Task> tasks = taskService.filterTasksByDueDateAndCompletionStatus(dueDate, complete.get());
        return ResponseEntity.ok(tasks);
    } else if (dueDate != null) {
        List<Task> tasks = taskService.filterTasksByDueDate(dueDate);
        return ResponseEntity.ok(tasks);
    } else if (complete.isPresent()) {
        List<Task> tasks = taskService.filterTasksByCompletionStatus(complete.get());
        return ResponseEntity.ok(tasks);
    } else {
        return ResponseEntity.badRequest().build();
    }
}


    
}
