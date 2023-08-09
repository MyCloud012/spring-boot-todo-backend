package com.example.tasksapp.entity;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
     @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
    private boolean complete;
}
