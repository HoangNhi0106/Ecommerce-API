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

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.getById(categoryId);
    }

    public Category getCategoryByName(String cname) {
        return categoryRepository.findCategoryByCname(cname);
    }

    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }
}
