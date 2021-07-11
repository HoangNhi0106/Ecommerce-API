package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long product_id) {
        return productRepository.findById(product_id).get();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long product_id) {
        productRepository.deleteById(product_id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
