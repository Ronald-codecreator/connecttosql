package com.test.connecttosql.service;

import com.test.connecttosql.entity.Task;
import com.test.connecttosql.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public Task saveTask(Task task){
       return taskRepository.save(task);
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }
}
