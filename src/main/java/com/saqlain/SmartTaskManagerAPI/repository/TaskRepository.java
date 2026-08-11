package com.saqlain.SmartTaskManagerAPI.repository;

import com.saqlain.SmartTaskManagerAPI.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
}
