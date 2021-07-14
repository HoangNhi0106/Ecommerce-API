package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();

    public Category getCategoryById(Long category_id);

    public Category getCategoryByName(String name);

    public Category addCategory(Category category);
}
