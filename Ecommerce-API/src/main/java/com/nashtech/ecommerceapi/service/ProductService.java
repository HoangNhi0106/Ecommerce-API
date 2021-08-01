package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Brand;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProducts() throws DataNotFoundException;

    public Product getProductById(Long productId) throws DataNotFoundException;

    public List<Product> getAllSortById();

    public List<Product> getByNameContainting(String name);

    public List<Product> getProductByCategory(Category category);

    public Product addProduct(Product product) throws CreateDataFailException;

    public void deleteProduct(Long productId) throws DeleteDataFailException;

    public void updateProduct(Product product) throws UpdateDataFailException;

    public List<Product> getProductByBrand(Brand brand);

    public Float calculateRatingStar(Product product) throws DataNotFoundException;
}
