package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.ProductException;
import com.nashtech.ecommerceapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ModelMapper modelMapper;

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProduct_id(product.getProduct_id());
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) throws ParseException {
        Product product = modelMapper.map(productDTO, Product.class);
        return product;
    }

    @GetMapping
    public List<ProductDTO> findAllProduct() {
        List<Product> products = productService.getAllProduct();
        List<ProductDTO> productDTOs = new ArrayList<>();
        for (Product product : products) {
            productDTOs.add(convertToDto(product));
        }
        return productDTOs;
    }

    @GetMapping(value = "/{id}")
    public ProductDTO findProduct(@PathVariable Integer id) {
        Product product = productService.getProduct(id);
        if (product == null)
            throw new ProductException(id);
        return convertToDto(product);
    }

    @PostMapping(value = "/save")
    public ProductDTO createPost(@Valid @RequestBody ProductDTO productDTO) throws ParseException {
        Product product = convertToEntity(productDTO);
        Product saveProduct = productService.addProduct(product);
        return convertToDto(saveProduct);
    }
}
