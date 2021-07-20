package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.CategoryConverter;
import com.nashtech.ecommerceapi.converter.ProductConverter;
import com.nashtech.ecommerceapi.converter.RatingConverter;
import com.nashtech.ecommerceapi.dto.CategoryDTO;
import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.service.AccountService;
import com.nashtech.ecommerceapi.service.CategoryService;
import com.nashtech.ecommerceapi.service.ProductService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    //Service
    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RatingService ratingService;

    //Converter
    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ProductConverter productConverter;

    //AccountController
    @DeleteMapping(value = "/account/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteAccount(@PathVariable Long id) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        Account account = accountService.getAccountById(id);
        accountService.deleteAccount(id);
        responseDTO.setData(true);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_USER_DELETED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //CategoryController
    @PostMapping(value = "/category/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> saveCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws ParseException, CreateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryConverter.convertToEntity(categoryDTO);
        Category saveCategory = categoryService.addCategory(category);
        responseDTO.setData(categoryConverter.convertToDto(saveCategory));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(value = "/category/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO) throws UpdateDataFailException, ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryConverter.convertToEntity(categoryDTO);
        categoryService.updateCategory(category);
        responseDTO.setData(categoryConverter.convertToDto(category));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "/product/delete/{productId}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long productId) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        productService.deleteProduct(productId);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_DELETED);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(value = "/product/update")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) throws ParseException, UpdateDataFailException, DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productConverter.convertToEntity(productDTO);
        productService.updateProduct(product);
        responseDTO.setData(productConverter.convertToDto(product));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_UPDATED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //ProductController
    @PostMapping(value = "/product/save")
    public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) throws CreateDataFailException, DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productConverter.convertToEntity(productDTO);
        Product saveProduct = productService.addProduct(product);
        responseDTO.setData(productConverter.convertToDto(saveProduct));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_SAVED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //RatingController
    @DeleteMapping(value = "/rating/delete/{ratingId}")
    public ResponseEntity<ResponseDTO> deleteRating(@PathVariable Long ratingId) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(true);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_DELETED);
        ratingService.deleteRating(ratingId);
        return ResponseEntity.ok().body(responseDTO);
    }
}
