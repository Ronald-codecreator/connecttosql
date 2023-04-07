package com.test.connecttosql.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subtask")
public class SubTask {
    @Id
    private int subTaskId;
    private int subTaskName;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "task_id", referencedColumnName = "taskId")
    @JsonIgnoreProperties("subTaskList")
    private Task task;
}
