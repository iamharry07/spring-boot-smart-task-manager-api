package com.saqlain.SmartTaskManagerAPI.controller;

import com.saqlain.SmartTaskManagerAPI.dto.request.CategoryRequest;
import com.saqlain.SmartTaskManagerAPI.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class CategoryController {
    private final CategoryService service;

    @PostMapping("/category")
    public void createCategory(@RequestBody @Valid CategoryRequest request){
        service.createCategory(request);
    }
}
