package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.*;
import com.nashtech.ecommerceapi.dto.*;
import com.nashtech.ecommerceapi.entity.*;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @Autowired
    private RatingConverter ratingConverter;

    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageConverter imageConverter;

    //AccountController
    @GetMapping(value = "/account")
    public ResponseEntity<ResponseDTO> findAllAccount() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Account> accounts = accountService.getAllAccount();
        List<AccountDTOAdmin> accountDTOAdmins = new ArrayList<>();
        if (accounts != null) {
            for (Account account : accounts)
                accountDTOAdmins.add(accountConverter.convertToDtoAdmin(account));
        }
        responseDTO.setData(accountDTOAdmins);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_USER_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

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
    @GetMapping("/category")
    public ResponseEntity<ResponseDTO> findAllCategory() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        if (categories != null) {
            for (Category category : categories)
                categoryDTOs.add(categoryConverter.convertToDto(category));
        }
        responseDTO.setData(categoryDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(value = "/category/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> saveCategory(@Valid @RequestBody CategoryDTOCreate categoryDTOCreate) throws ParseException, CreateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryConverter.convertToEntityCreate(categoryDTOCreate);
        Category saveCategory = categoryService.addCategory(category);
        responseDTO.setData(categoryConverter.convertToDto(saveCategory));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(value = "/category/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> updateCategory(@Valid @RequestBody CategoryDTOUpdate categoryDTOUpdate) throws UpdateDataFailException, ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryConverter.convertToEntityUpdate(categoryDTOUpdate);
        categoryService.updateCategory(category);
        responseDTO.setData(categoryConverter.convertToDto(category));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_SAVED);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "/category/delete/{categoryId}")
    public ResponseEntity<ResponseDTO> deleteCategory(@PathVariable Long categoryId) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        categoryService.deleteCategory(categoryId);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_DELETED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //ProductController
    @GetMapping("/product")
    public ResponseEntity<ResponseDTO> findAllProduct() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        if (products != null)
            for (Product product : products)
                productDTOs.add(productConverter.convertToDto(product));
        responseDTO.setData(productDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping("/product/sort=id")
    public ResponseEntity<ResponseDTO> findAllProductSortById() {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> products = productService.getAllSortById();
        if (products != null)
            for (Product product : products)
                productDTOs.add(productConverter.convertToDto(product));
        responseDTO.setData(productDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(value = "/product/save")
    public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody ProductDTOCreate productDTOCreate) throws CreateDataFailException, DataNotFoundException, IOException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productConverter.convertToEntityCreate(productDTOCreate);
        Product saveProduct = productService.addProduct(product);
        responseDTO.setData(productConverter.convertToDto(saveProduct));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_SAVED);
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
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTOUpdate productDTOUpdate) throws ParseException, UpdateDataFailException, DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productConverter.convertToEntityUpdate(productDTOUpdate);
        productService.updateProduct(product);
        responseDTO.setData(productConverter.convertToDto(product));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_UPDATED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //RatingController
    @GetMapping("/rating")
    public ResponseEntity<ResponseDTO> findAllRating() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Rating> ratings = ratingService.getAllRatings();
        List<RatingDTOShow> ratingDTOShows = new ArrayList<>();
        if (ratings != null)
            for (Rating rating : ratings) {
                ratingDTOShows.add(ratingConverter.convertToDtoShow(rating));
            }
        responseDTO.setData(ratingDTOShows);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "/rating/delete/{ratingId}")
    public ResponseEntity<ResponseDTO> deleteRating(@PathVariable Long ratingId) throws DeleteDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        ratingService.deleteRating(ratingId);
        responseDTO.setData(true);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_DELETED);
        return ResponseEntity.ok().body(responseDTO);
    }

    //ImageComtroller
    @PostMapping("/image/save")
    public ResponseEntity<ResponseDTO> uploadImage(@RequestParam("file") MultipartFile file) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Image image = imageService.saveImage(file);
            ImageDTO imageDTO = imageConverter.convertToDto(image);
            responseDTO.setData(imageDTO);
            responseDTO.setSuccessCode(SuccessCode.SUCCESS_IMAGE_SAVED);
        } catch (Exception e) {
            responseDTO.setData(null);
            responseDTO.setErrorCode(ErrorCode.ERROR_IMAGE_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
