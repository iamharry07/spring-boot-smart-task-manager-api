package com.saqlain.SmartTaskManagerAPI.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterRequest {

    @NotBlank
    private String fullName;

    @Email(message = "{register.email.invalid}")
    @NotBlank
    private String email;

    @Size(min = 6, max = 15, message = "{register.password.size}")
    @NotBlank
    private String password;

}
