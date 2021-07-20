package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.*;
import com.nashtech.ecommerceapi.dto.*;
import com.nashtech.ecommerceapi.entity.*;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/public")
public class PublicController {
    //Service
    @Autowired
    private AccountService accountService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ImageService imageService;

    //Converter
    @Autowired
    private AccountConverter accountConverter;

    @Autowired
    private CategoryConverter categoryConverter;

    @Autowired
    private ProductConverter productConverter;

    @Autowired
    private RatingConverter ratingConverter;

    @Autowired
    private ImageConverter imageConverter;

    //AccountController
    @GetMapping("/account/{id}")
    public ResponseEntity<ResponseDTO> findAccountById(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        Account account = accountService.getAccountById(id);
        responseDTO.setData(accountConverter.convertToDto(account));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_USER_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    //CategoryController
    @GetMapping("/category")
    public ResponseEntity<ResponseDTO> findAllCategory() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Category> categories = categoryService.getAllCategories();
        List<CategoryDTO> categoryDTOs = new ArrayList<>();
        for (Category category : categories)
            categoryDTOs.add(categoryConverter.convertToDto(category));
        responseDTO.setData(categoryDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/category/{cname}")
    public ResponseEntity<ResponseDTO> findCategoryByName(@PathVariable String cname) throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Category category = categoryService.getCategoryByName(cname);
        responseDTO.setData(categoryConverter.convertToDto(category));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_CATEGORY_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    //ProductController
    @GetMapping("/product")
    public ResponseEntity<ResponseDTO> findAllProduct() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products)
            productDTOs.add(productConverter.convertToDto(product));
        responseDTO.setData(productDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/product/{id}")
    public ResponseEntity<ResponseDTO> findProduct(@PathVariable Long id) throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productService.getProductById(id);
        responseDTO.setData(productConverter.convertToDto(product));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    //RatingController
    @GetMapping("/rating")
    public ResponseEntity<ResponseDTO> findAllRating() throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<Rating> ratings = ratingService.getAllRatings();
        List<RatingDTO> ratingDTOs = new ArrayList<>();
        for (Rating rating : ratings) {
            ratingDTOs.add(ratingConverter.convertToDto(rating));
        }
        responseDTO.setData(ratingDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_RATING_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    //ImageController
    @GetMapping("/image")
    public ResponseEntity<ResponseDTO> findAllImages() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<ImageDTO> imageDTOs = new ArrayList<>();
            List<Image> images = imageService.getAllImages();
            for (Image image : images) {
                imageDTOs.add(imageConverter.convertToDto(image));
            }
            if (imageDTOs != null) {
                responseDTO.setData(imageDTOs);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_IMAGE_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_IMAGE_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}