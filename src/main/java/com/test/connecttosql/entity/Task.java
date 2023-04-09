package com.test.connecttosql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

//Requirement of Task Management application
//        1. Add new task (task can have subtask as well)
//        2. Get All task and Get task by id
//        3. Update task details
//        4. Delete selected task ids ( If parent task is deleted, it's all subtask should be deleted)
//        5. Search task by any field
//        6. Export and Import task details from CSV file
//
//        Task Modal
//        id
//        Title
//        Description
//        State -> New, Open, Completed, Close, Not Required
//        priority -> Low, Medium, High
//        Related Work Link
//        Created date
//        Updated date
//        Assign To - String
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="task_seq", allocationSize=1)
    private int taskId;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private State state;
    @Enumerated(EnumType.STRING)
    private Priority priority;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private String assignTo;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
    @JoinColumn(name ="task_id",referencedColumnName = "taskId")
    private List<SubTask> subTaskList = new ArrayList<>();

    @PrePersist
    public void setCreatedOn() {
        this.createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void setUpdatedOn() {
        this.updatedOn = LocalDateTime.now();
    }
}
