package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.dto.request.TaskRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.TaskResponse;
import com.saqlain.SmartTaskManagerAPI.entity.Category;
import com.saqlain.SmartTaskManagerAPI.entity.Tasks;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import com.saqlain.SmartTaskManagerAPI.repository.CategoryRepository;
import com.saqlain.SmartTaskManagerAPI.repository.TaskRepository;
import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


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

        Tasks task = new Tasks();

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

    public List<TaskResponse> getMyTasks() {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Tasks> tasks = taskRepository.findAllByUserEmail(email);

        return tasks.stream().map(task -> new TaskResponse(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCategory().getName(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt())).toList();

    }

}
