package com.test.connecttosql.controller;

import com.test.connecttosql.entity.SubTask;
import com.test.connecttosql.entity.Task;
import com.test.connecttosql.repository.TaskRepository;
import com.test.connecttosql.service.TaskService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class TaskController {
    private TaskService taskService;
    private TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskService taskService, TaskRepository taskRepository) {
        this.taskService = taskService;
        this.taskRepository = taskRepository;
    }

    @GetMapping("/taskById/{id}")
    public Task findTaskById(@PathVariable int id) {
        return taskService.getTaskById(id);
    }

    @GetMapping("/search")
    public List<Task> searchByKeyword(@RequestParam("keyword") String keyword) {
        return taskService.searchByKeyword(keyword);
    }

    @GetMapping("/export/tasks")
    public void exportParents(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"tasks.csv\"");

        List<Task> tasks = taskRepository.findAll();
        PrintWriter writer = response.getWriter();

        writer.println("title,description,subTaskList");
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

    @PostMapping("/import/tasks")
    public ResponseEntity<String> importParents(@RequestParam("file") MultipartFile file) throws IOException {
        List<Task> parents = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));

        // skip the header line
        reader.readLine();

        String line = "";
        String cvsSplitBy = ",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"; // regex pattern

        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(cvsSplitBy);

            String title = fields[0];
            String description = fields[1];
            String[] childrenNames = fields[2].split(",");
            Task parent = new Task();
            parent.setTitle(title);
            parent.setDescription(description);
            if (fields[2].contains(",")) {
                if (childrenNames.length > 0) {
                    childrenNames[0] = childrenNames[0].replaceAll("^\"|\"$", "");
                    childrenNames[childrenNames.length - 1] = childrenNames[childrenNames.length - 1].replaceAll("^\"|\"$", "");// remove leading/trailing quotes
                }


                List<SubTask> children = new ArrayList<>();
                for (String childName : childrenNames) {
                    SubTask child = new SubTask();
                    child.setDescription(childName.trim());
                    child.setTask(parent);
                    children.add(child);
                }

                parent.setSubTaskList(children);
                parents.add(parent);
            }
        }

        taskRepository.saveAll(parents);
        return ResponseEntity.ok("CSV imported successfully");
    }


}
