package com.test.connecttosql.service;

import com.test.connecttosql.entity.Task;

public interface TaskService {
    Task saveTask(Task task);
    Task getTaskById(int id) ;

}
