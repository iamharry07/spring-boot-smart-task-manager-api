package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.TaskRequest;
import com.saqlain.SmartTaskManagerAPI.dto.response.TaskResponse;
import com.saqlain.SmartTaskManagerAPI.entity.Tasks;
import com.saqlain.SmartTaskManagerAPI.service.TaskService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

}
