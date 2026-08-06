package com.saqlain.SmartTaskManagerAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String fullName;

    @Email(message = "{register.email.invalid}")
    @NotBlank
    @Column(unique = true)
    private String email;

    @Size(min = 6, max = 15, message = "{register.password.size}")
    @NotBlank
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull
    private Role role;

    @NotNull
    private Timestamp createdAt;


}
