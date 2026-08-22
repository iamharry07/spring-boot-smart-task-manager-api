package com.saqlain.SmartTaskManagerAPI.service;

import com.saqlain.SmartTaskManagerAPI.dto.request.CategoryRequest;
import com.saqlain.SmartTaskManagerAPI.entity.Category;
import com.saqlain.SmartTaskManagerAPI.entity.Users;
import com.saqlain.SmartTaskManagerAPI.repository.CategoryRepository;
import com.saqlain.SmartTaskManagerAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public void createCategory(CategoryRequest request){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        Users users = userRepository.findByEmail(email).orElseThrow();

        Category category = new Category();

        category.setName(request.getName());
        category.setColour(request.getColour());
        category.setUser(users);

        categoryRepository.save(category);
    }
}
