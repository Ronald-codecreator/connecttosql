package com.test.connecttosql.controller;

import com.test.connecttosql.entity.Task;
import com.test.connecttosql.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/addTask")
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    @GetMapping("/taskById/{id}")
    public Task findTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }
}
