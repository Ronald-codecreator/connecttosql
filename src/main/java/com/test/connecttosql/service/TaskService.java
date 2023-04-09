package com.test.connecttosql.service;

import com.test.connecttosql.entity.Task;
import com.test.connecttosql.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;
    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> searchByKeyword(String keyword) {
        return taskRepository.findByKeyword(keyword);
    }
}
