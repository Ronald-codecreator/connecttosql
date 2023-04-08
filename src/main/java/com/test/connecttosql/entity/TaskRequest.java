package com.test.connecttosql.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {
    private String title;
    private String description;
    private State state;
    private Priority priority;
    private Date createdOn;
    private Date updatedOn;
    private String assignTo;
    private List<String> subTasks;

}
