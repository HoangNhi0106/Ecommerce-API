package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.repository.CategoryRepository;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.service.CategoryService;
import com.nashtech.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductService productService;

    public List<Category> getAllCategories() {
        List<Category> categories =  categoryRepository.findAll();
        if (!categories.isEmpty()) {
            return categories;
        } else
            return null;
    }

    public Category getCategoryById(Long categoryId) throws DataNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent())
            return optionalCategory.get();
        else
            throw new DataNotFoundException(ErrorCode.ERROR_CATEGORY_NOT_FOUND);
    }

    public Category getCategoryByName(String cname) throws DataNotFoundException {
        Optional<Category> optionalCategory = categoryRepository.findCategoryByCname(cname);
        if (optionalCategory.isPresent())
            return optionalCategory.get();
        else
            throw new DataNotFoundException(ErrorCode.ERROR_CATEGORY_NOT_FOUND);
    }

    public Category addCategory(Category category) throws CreateDataFailException {
        category.setCreatedIn(LocalDateTime.now());
        Category saveCategory = categoryRepository.save(category);
        if (saveCategory != null)
            return category;
        else
            throw new CreateDataFailException(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
    }

    public void updateCategory(Category category) throws UpdateDataFailException {
        Category currentCategory = categoryRepository.getById(category.getCategoryId());
        currentCategory.setDescription(category.getDescription());
        currentCategory.setUpdatedIn(LocalDateTime.now());
        Category c = categoryRepository.save(currentCategory);
        if (c == null)
            throw new UpdateDataFailException(ErrorCode.ERROR_CATEGORY_NOT_UPDATED);
    }

    public void deleteCategory(Long categoryId) throws DeleteDataFailException {
        try {
            Category category = getCategoryById(categoryId);
            List<Product> products = productService.getProductByCategory(category);
            if (products != null)
                for (Product product : products)
                    productService.deleteProduct(product.getProductId());
            categoryRepository.deleteById(categoryId);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERROR_CATEGORY_NOT_DELETED);
        }

    }
}
