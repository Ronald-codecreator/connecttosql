package com.test.TaskManagementSystem.service;

import com.test.TaskManagementSystem.entity.SubTask;
import com.test.TaskManagementSystem.entity.Task;
import com.test.TaskManagementSystem.repository.TaskRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> searchByKeyword(String keyword) {
        return taskRepository.findByKeyword(keyword);
    }

    public void exportTasks(HttpServletResponse response) throws IOException {
        List<Task> tasks = taskRepository.findAll();
        PrintWriter writer = response.getWriter();

        writer.println("title,description,subTaskList");//currently only exporting 3 header and not all
        for (Task task : tasks) {
            StringBuilder children = new StringBuilder();
            for (SubTask child : task.getSubTaskList()) {
                children.append(child.getDescription()).append(",");
            }
            String subTasks = children.toString().replaceAll(",$", "");
            writer.write(task.getTitle() + "," + task.getDescription() + ",\"" + subTasks + "\"\n");
        }

        writer.flush();
        writer.close();
    }

    public void importTasks(List<Task> tasks) {
        taskRepository.saveAll(tasks);
    }
}
