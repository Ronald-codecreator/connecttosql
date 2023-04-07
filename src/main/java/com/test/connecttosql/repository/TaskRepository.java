package com.test.connecttosql.repository;

import com.test.connecttosql.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository <Task,Integer> {
}
