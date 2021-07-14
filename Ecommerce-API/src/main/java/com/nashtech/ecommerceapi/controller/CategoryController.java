package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.dto.CategoryDTO;
import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.CategoryException;
import com.nashtech.ecommerceapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    private CategoryDTO convertToDto(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        categoryDTO.setCategory_id(category.getCategory_id());
        return categoryDTO;
    }

    private Category convertToEntity(CategoryDTO categoryDTO) throws ParseException {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return category;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> findAllCategory() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Category> categories = categoryService.getAllCategory();
            if (categories != null) {
                for (Category category : categories) {
                    responseDTO.setData(convertToDto(category));
                }
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_CATEGORY_NOT_FOUND);
            throw new CategoryException(ErrorCode.ERROR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{cname}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> findCategoryByName(@PathVariable String cname) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Category category = categoryService.getCategoryByName(cname);
            if (category != null) {
                responseDTO.setData(convertToDto(category));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_CATEGORY_NOT_FOUND);
            throw new CategoryException(ErrorCode.ERROR_CATEGORY_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Category category = convertToEntity(categoryDTO);
            Category saveCategory = categoryService.addCategory(category);
            if (saveCategory != null) {
                responseDTO.setData(convertToDto(category));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
            throw new CategoryException(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
