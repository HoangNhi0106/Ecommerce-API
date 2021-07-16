package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.constant.SuccessCode;
import com.nashtech.ecommerceapi.converter.ProductConverter;
import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.dto.ResponseDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.ProductException;
import com.nashtech.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductConverter productConverter;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> findAllProduct() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            List<ProductDTO> productDTOs = new ArrayList<>();
            List<Product> products = productService.getAllProduct();
            for (Product product : products) {
                productDTOs.add(productConverter.convertToDto(product));
            }
            if (productDTOs != null) {
                responseDTO.setData(productDTOs);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
            throw new ProductException(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO> findProduct(@PathVariable Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productService.getProductById(id);
            if (product != null) {
                responseDTO.setData(productConverter.convertToDto(product));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
            }

        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
            throw new ProductException(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) throws ParseException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productConverter.convertToEntity(productDTO);
            Product saveProduct = productService.addProduct(product);
            if (saveProduct != null) {
                responseDTO.setData(productConverter.convertToDto(product));
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_SAVED);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_PRODUCT_NOT_SAVED);
            throw new ProductException(ErrorCode.ERROR_PRODUCT_NOT_SAVED);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @DeleteMapping(value = "delete/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable Long productId) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productService.getProductById(productId);
            if (product != null) {
                productService.deleteProduct(productId);
                responseDTO.setData(true);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setData(false);
            responseDTO.setErrorCode(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
            throw new ProductException(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Product product = productConverter.convertToEntity(productDTO);
            if (productDTO != null) {
                productService.updateProduct(product);
                responseDTO.setData(productDTO);
                responseDTO.setSuccessCode(SuccessCode.SUCCESS_PRODUCT_FOUND);
            }
        } catch (Exception exception) {
            responseDTO.setErrorCode(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
            throw new ProductException(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
        }
        return ResponseEntity.ok().body(responseDTO);
    }
}
