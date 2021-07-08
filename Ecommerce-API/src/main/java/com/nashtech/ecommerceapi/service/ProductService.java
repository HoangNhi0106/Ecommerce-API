package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();

    public Product getProduct(Integer product_id);

    public Product addProduct(Product product);

    public void deleteProduct(Integer product_id);

    public void updateProduct(Product product);
}
