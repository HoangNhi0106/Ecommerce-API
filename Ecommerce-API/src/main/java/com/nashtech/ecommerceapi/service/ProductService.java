package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    public Product getProduct(Integer product_id);

    public Product addProduct(Product product);

    public void deleteProduct(Integer product_id);

    public void updateProduct(Product product);
}
