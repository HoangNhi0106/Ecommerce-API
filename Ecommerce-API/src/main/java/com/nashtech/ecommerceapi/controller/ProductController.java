package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.ProductException;
import com.nashtech.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public Product findStudent(@PathVariable Integer id) {
        Product product = productService.getProduct(id);
        if (product == null)
            throw new ProductException(id);
        return product;
    }
}
