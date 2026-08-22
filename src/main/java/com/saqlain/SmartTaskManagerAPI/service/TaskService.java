package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.dto.request.TaskRequest;
import com.saqlain.SmartTaskManagerAPI.entity.Category;
import com.saqlain.SmartTaskManagerAPI.entity.Task;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import com.saqlain.SmartTaskManagerAPI.repository.CategoryRepository;
import com.saqlain.SmartTaskManagerAPI.repository.TaskRepository;
import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;


@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public void createTask(TaskRequest request) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Users users = userRepository.findByEmail(email).orElseThrow();
        Category category = categoryRepository.findByNameAndUser(request.getCategory(), users).orElseThrow();

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        task.setUser(users);
        task.setCategory(category);

        taskRepository.save(task);
    }

}
