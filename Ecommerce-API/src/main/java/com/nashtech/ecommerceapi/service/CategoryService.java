package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategory();

    public Category getCategoryById(Long categoryId);

    public Category getCategoryByName(String cname);

    public Category addCategory(Category category);
}
