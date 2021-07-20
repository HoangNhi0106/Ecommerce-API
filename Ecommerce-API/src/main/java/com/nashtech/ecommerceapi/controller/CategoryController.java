package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.CategoryConverter;
import com.nashtech.ecommerceapi.dto.CategoryDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.exception.CategoryException;
import com.nashtech.ecommerceapi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryConverter categoryConverter;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseDTO> findAllCategory() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<Category> categories = categoryService.getAllCategories();
            if (categories != null) {
                for (Category category : categories) {
                    responseDTO.setData(categoryConverter.convertToDto(category));
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
    @PreAuthorize("permitAll()")
    public ResponseEntity<ResponseDTO> findCategoryByName(@PathVariable String cname) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Category category = categoryService.getCategoryByName(cname);
            if (category != null) {
                responseDTO.setData(categoryConverter.convertToDto(category));
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
    public ResponseEntity<ResponseDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Category category = categoryConverter.convertToEntity(categoryDTO);
            category.setCreatedIn(LocalDateTime.now());
            Category saveCategory = categoryService.addCategory(category);
            if (saveCategory != null) {
                responseDTO.setData(categoryConverter.convertToDto(category));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
            throw new CategoryException(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Category category = categoryConverter.convertToEntity(categoryDTO);
            category.setUpdatedIn(LocalDateTime.now());
            categoryService.updateCategory(category);
            if (category != null) {
                responseDTO.setData(categoryConverter.convertToDto(category));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
            throw new CategoryException(ErrorCode.ERROR_CATEGORY_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
