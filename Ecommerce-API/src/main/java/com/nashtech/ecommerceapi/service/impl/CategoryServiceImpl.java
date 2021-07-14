package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.repository.CategoryRepository;
import com.nashtech.ecommerceapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long category_id) {
        return categoryRepository.getById(category_id);
    }

    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
