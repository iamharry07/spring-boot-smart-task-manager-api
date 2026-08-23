package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.TaskRequest;
import com.saqlain.SmartTaskManagerAPI.dto.request.UpdateTaskRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.TaskResponse;
import com.saqlain.SmartTaskManagerAPI.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskResponse> getTasks() {
        return taskService.getMyTasks();
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody @Valid TaskRequest request){
        taskService.createTask(request);
    }

    @GetMapping("/tasks/{id}")
    public TaskResponse getTaskById(@PathVariable Long id){
        return taskService.getMyTaskById(id);
    }

    @PutMapping("/tasks/{id}")
    public TaskResponse updateTask(@PathVariable Long id,@RequestBody @Valid UpdateTaskRequest request){
        return taskService.updateTask(id,request);
    }

    @DeleteMapping("/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
    }

    @GetMapping("/tasks/status/{status}")
    public List<TaskResponse> getTaskByStatus(@PathVariable String status){
        return taskService.searchTaskByStatus(status);
    }

    @GetMapping("/tasks/priority/{priority}")
    public List<TaskResponse> getTaskByPriority(@PathVariable String priority){
        return taskService.getMyTasksByPriority(priority);
    }

    @GetMapping("/tasks/category/{category}")
    public List<TaskResponse> getTaskByCategory(@PathVariable String category){
        return taskService.getTasksByCategoryName(category);
    }

    @GetMapping("/tasks/title/{title}")
    public List<TaskResponse> getTaskByTitle(@PathVariable String title){
        return taskService.getTasksByTitle(title);
    }

    @GetMapping("/task")
    public Page<TaskResponse> getMyTasks(Pageable page){
        return taskService.getMyTasks(page);
    }

}
