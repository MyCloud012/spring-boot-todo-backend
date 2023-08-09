package com.example.tasksapp.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskRequest {
    private String description;
    private LocalDate dueDate;
}

