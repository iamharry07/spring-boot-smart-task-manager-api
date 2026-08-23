package com.saqlain.SmartTaskManagerAPI.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateTaskRequest {
    @NotBlank
    private String status;
}
