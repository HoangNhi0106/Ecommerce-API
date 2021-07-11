package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.ProductException;
import com.nashtech.ecommerceapi.service.CategoryService;
import com.nashtech.ecommerceapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    private ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProduct_id(product.getProduct_id());
        productDTO.setCategory_name(product.getCategory().getName());
        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) throws ParseException {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(categoryService.getCategoryByName(productDTO.getCategory_name()));
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
    public ProductDTO findProduct(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product == null)
            throw new ProductException(id);
        return convertToDto(product);
    }

    @PostMapping(value = "/save")
    public ProductDTO createProduct(@Valid @RequestBody ProductDTO productDTO) throws ParseException {
        Product product = convertToEntity(productDTO);
        Product saveProduct = productService.addProduct(product);
        return convertToDto(saveProduct);
    }

    @DeleteMapping(value = "delete/{product_id}")
    public HashMap<String, String> deleteProduct(@PathVariable Long product_id) {
        Product product = productService.getProductById(product_id);
        if (product == null)
            throw new ProductException(product_id);
        productService.deleteProduct(product_id);
        HashMap<String, String> map = new HashMap<>();
        map.put("delete product id = " + product_id, "successful");
        return map;
    }

    @PutMapping(value = "/update")
    public HashMap<String, String> updateProduct(@Valid @RequestBody Product product) {
        if (product == null)
            throw new ProductException(product.getProduct_id());
        productService.updateProduct(product);
        HashMap<String, String> map = new HashMap<>();
        map.put("update product id = " + product.getProduct_id(), "successful");
        return map;
    }
}
