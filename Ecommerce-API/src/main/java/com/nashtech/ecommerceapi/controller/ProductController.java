package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.configuration.ModelMapperConfig;
import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.ProductException;
import com.nashtech.ecommerceapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /*@Autowired
    private ModelMapper modelMapper;

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        return productDTO;
    }*/

    @GetMapping
    public List<Product> findAllProduct() {
        return productService.getAllProduct();
    }

    @GetMapping(value = "/{id}")
    public Product findProduct(@PathVariable Integer id) {
        Product product = productService.getProduct(id);
        if (product == null)
            throw new ProductException(id);
        return product;
    }
}
