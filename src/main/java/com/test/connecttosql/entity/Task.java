package com.test.connecttosql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Table(name = "task")
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int taskId;
    private String title;
    private String description;
    private State state;
    private Priority priority;
    private Date createdOn;
    private Date updatedOn;
    private String assignTo;
    @OneToMany(mappedBy = "task",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JsonIgnoreProperties("task")
    private List<SubTask> subTaskList ;
}
