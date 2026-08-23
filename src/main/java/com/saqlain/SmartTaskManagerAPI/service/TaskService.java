package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.dto.request.TaskRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.UpdateTaskRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.TaskResponse;
import com.saqlain.SmartTaskManagerAPI.entity.Category;
import com.saqlain.SmartTaskManagerAPI.entity.Tasks;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import com.saqlain.SmartTaskManagerAPI.exception.TaskNotFoundException;
import com.saqlain.SmartTaskManagerAPI.repository.CategoryRepository;
import com.saqlain.SmartTaskManagerAPI.repository.TaskRepository;
import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    private String getCurrentUser() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public void createTask(TaskRequest request) {

        Users users = userRepository.findByEmail(getCurrentUser()).orElseThrow();
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

        List<Tasks> tasks = taskRepository.findAllByUserEmail(getCurrentUser());

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

    public TaskResponse getMyTaskById(Long id) {

        Optional<Tasks> tasks = taskRepository.getTaskByIdAndUserEmail(id, getCurrentUser());
        return tasks.map(task -> new TaskResponse(task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getPriority(),
                task.getCategory().getName(),
                task.getDueDate(),
                task.getCreatedAt(),
                task.getUpdatedAt())).orElseThrow();
    }

    public TaskResponse updateTask(Long id, UpdateTaskRequest request) {
        Optional<Tasks> tasks = taskRepository.getTaskByIdAndUserEmail(id, getCurrentUser());
        if (tasks.isPresent()) {
            Tasks task = tasks.get();
            task.setStatus(request.getStatus());
            task.setUpdatedAt(LocalDateTime.now());

            taskRepository.save(task);

            return new TaskResponse(task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getCategory().getName(),
                    task.getDueDate(),
                    task.getCreatedAt(),
                    task.getUpdatedAt());
        } else {
            throw new TaskNotFoundException("Task Not Found");
        }

    }

    public void deleteTask(Long id) {
        Optional<Tasks> tasks = taskRepository.getTaskByIdAndUserEmail(id, getCurrentUser());
        if (tasks.isPresent()) {
            Tasks tasks1 = tasks.get();
            taskRepository.delete(tasks1);
        } else {
            throw new TaskNotFoundException("Task Not Found");
        }

    }

    public List<TaskResponse> searchTaskByStatus(String status) {
        List<Tasks> tasks = taskRepository.findTaskByUserEmailAndStatus(getCurrentUser(), status);
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

    public List<TaskResponse> getMyTasksByPriority(String priority) {
        List<Tasks> tasks = taskRepository.findTaskByUserEmailAndPriority(getCurrentUser(), priority);
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

    public List<TaskResponse> getTasksByCategoryName(String category) {
        List<Tasks> tasks = taskRepository.findTaskByUserEmailAndCategoryName(getCurrentUser(), category);
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

    public List<TaskResponse> getTasksByTitle(String title) {
        List<Tasks> tasks = taskRepository.findTaskByUserEmailAndTitleContainingIgnoreCase(getCurrentUser(), title);
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
