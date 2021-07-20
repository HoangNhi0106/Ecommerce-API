package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories() throws DataNotFoundException;

    public Category getCategoryById(Long categoryId) throws DataNotFoundException;

    public Category getCategoryByName(String cname) throws DataNotFoundException;

    public Category addCategory(Category category) throws CreateDataFailException;

    public void updateCategory(Category category) throws UpdateDataFailException;

    public void deleteCategory(Long categoryId) throws DeleteDataFailException;
}
