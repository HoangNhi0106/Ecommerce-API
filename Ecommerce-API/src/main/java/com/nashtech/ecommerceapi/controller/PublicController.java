package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.*;
import com.nashtech.ecommerceapi.dto.*;
import com.nashtech.ecommerceapi.entity.*;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
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
        if (categories != null) {
            for (Category category : categories)
                categoryDTOs.add(categoryConverter.convertToDto(category));
        }
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
    @GetMapping("/product/search={name}")
    public ResponseEntity<ResponseDTO> searchProductByName(@PathVariable String name) throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        List<ProductDTO> productDTOs = new ArrayList<>();
        List<Product> products = productService.getByNameContainting(name);
        if (products != null)
            for (Product product : products)
                productDTOs.add(productConverter.convertToDto(product));
        responseDTO.setData(productDTOs);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/product/product={id}")
    public ResponseEntity<ResponseDTO> findProduct(@PathVariable Long id) throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productService.getProductById(id);
        responseDTO.setData(productConverter.convertToDto(product));
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/product/category={cname}")
    private ResponseEntity<ResponseDTO> findAllProductByCname(@PathVariable String cname) throws DataNotFoundException {
        ResponseDTO responseDTO =  new ResponseDTO();
        Category category = categoryService.getCategoryByName(cname);
        List<ProductDTOItem> productDTOItems = new ArrayList<>();
        List<Product> products = category.getProducts();
        for (Product product : products)
            productDTOItems.add(productConverter.convertToDtoItem(product));
        responseDTO.setData(productDTOItems);
        responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
        return ResponseEntity.ok().body(responseDTO);
    }

    //RatingController

    @GetMapping("/rating/product={id}")
    public ResponseEntity<ResponseDTO> findRatingByProduct(@PathVariable Long id) throws DataNotFoundException {
        ResponseDTO responseDTO = new ResponseDTO();
        Product product = productService.getProductById(id);
        List<Rating> ratings = ratingService.getRatingByProduct(product);
        List<RatingDTOReview> ratingDTOReviews = new ArrayList<>();
        for (Rating rating : ratings) {
            ratingDTOReviews.add(ratingConverter.convertToDtoReview(rating));
        }
        responseDTO.setData(ratingDTOReviews);
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

    @GetMapping("/image/{image_id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String image_id) {
        try {
            Image image = imageService.getImageById(image_id);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; imageId=\"" + image.getImageId() + "\"")
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .body(image.getData());
        } catch (Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }
}
