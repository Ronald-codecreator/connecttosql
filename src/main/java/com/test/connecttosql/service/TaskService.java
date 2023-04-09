package com.test.connecttosql.service;

import com.test.connecttosql.entity.Task;
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
