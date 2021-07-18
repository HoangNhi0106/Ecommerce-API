package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts();

    public Product getProductById(Long productId);

    public Product addProduct(Product product);

    public void deleteProduct(Long productId);

    public void updateProduct(Product product);

    public Float calculateRatingStar(Product product);
}
