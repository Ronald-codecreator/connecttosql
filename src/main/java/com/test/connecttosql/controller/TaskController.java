package com.test.connecttosql.controller;

import com.test.connecttosql.entity.SubTask;
import com.test.connecttosql.entity.Task;
import com.test.connecttosql.entity.TaskRequest;
import com.test.connecttosql.service.TaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    private TaskServiceImpl taskService;

    @PostMapping("/addTask")
    public Task addTask(@RequestBody TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setState(taskRequest.getState());
        task.setPriority(taskRequest.getPriority());
        task.setDescription(taskRequest.getDescription());
        task.setCreatedOn(taskRequest.getCreatedOn());
        task.setUpdatedOn(taskRequest.getUpdatedOn());
        task.setAssignTo(taskRequest.getAssignTo());

        List<SubTask> subTasks = new ArrayList<>();
        for (String subtaskDescription : taskRequest.getSubTasks()) {
            SubTask subTask = new SubTask();
            subTask.setDescription(subtaskDescription);
            subTask.setTask(task);
            subTasks.add(subTask);
        }
        task.setSubTaskList(subTasks);

        return taskService.saveTask(task);
    }
    @GetMapping("/taskById/{id}")
    public Task findTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }
}
