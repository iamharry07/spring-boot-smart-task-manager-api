package com.saqlain.SmartTaskManagerAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TaskRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String status;

    @NotBlank
    private String priority;

    @NotNull
    private LocalDate dueDate;

    @NotBlank
    private String category;

    public TaskRequest(String status){
        this.status = status;
    }
}
