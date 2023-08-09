package com.example.tasksapp;

import com.example.tasksapp.entity.Task;
import com.example.tasksapp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class TasksAppApplication implements ApplicationRunner {

    @Autowired
    private TaskRepository taskRepository;

    public static void main(String[] args) {
        SpringApplication.run(TasksAppApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Initialize the database with some data
        Task task1 = new Task();
        task1.setDescription("Task 1");
        task1.setDueDate(LocalDate.of(2023, 8, 10));
        task1.setComplete(false);
        taskRepository.save(task1);

        Task task2 = new Task();
        task2.setDescription("Task 2");
        task2.setDueDate(LocalDate.of(2023, 8, 15));
        task2.setComplete(true);
        taskRepository.save(task2);
    }
}
