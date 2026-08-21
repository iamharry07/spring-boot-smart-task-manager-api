package com.saqlain.SmartTaskManagerAPI.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String colour;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

}
