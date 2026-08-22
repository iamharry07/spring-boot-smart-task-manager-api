package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.TaskRequest;
import com.saqlain.SmartTaskManagerAPI.service.TaskService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/t")
    public String getTasks(){
        return "Hello";
    }

    @PostMapping("/tasks")
    public void createTask(@RequestBody @Valid TaskRequest request){
        taskService.createTask(request);
    }

}
