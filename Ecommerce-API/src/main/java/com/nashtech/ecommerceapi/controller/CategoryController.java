package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.exception.CategoryException;
import com.nashtech.ecommerceapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAllCategory() {
        return categoryService.getAllCategory();
    }

    @GetMapping(value = "/{cname}")
    public Category findCategoryByName(@PathVariable String cname) {
        Category category = categoryService.getCategoryByName(cname);
        if (category == null)
            throw new CategoryException(cname);
        return category;
    }

    @PostMapping(value = "/save")
    public Category saveCategory(@Valid @RequestBody Category category) {
        return categoryService.saveCategory(category);
    }
}
