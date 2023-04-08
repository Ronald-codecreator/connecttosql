package com.test.connecttosql.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id @GeneratedValue
    private int taskId;
    private String title;
    private String description;
    private State state;
    private Priority priority;
    private Date createdOn;
    private Date updatedOn;
    private String assignTo;
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubTask> subTaskList = new ArrayList<>();

}
