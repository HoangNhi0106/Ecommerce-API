package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceTest {
    @Autowired
    ProductService productService;

    @Test
    public void addProduct_thenSuccess() throws CreateDataFailException {
        Product product = new Product();
        Category category = new Category();
        category.setCategoryId(1l);
        category.setCname("xyz");
        product.setProductId(1);
        product.setPname("abc");
        product.setCategory(category);
        product.setAmount(50);
        product.setPrice(1000000);
        product.setDescription("hello");

        Product product1 = productService.addProduct(product);
        assertEquals(product.getProductId(), product1.getProductId());
        assertEquals(product.getPname(), product1.getPname());
        assertEquals(product.getPrice(), product1.getPrice());
        assertEquals(product.getDescription(), product1.getDescription());
        assertEquals(product.getCategory().getCategoryId(), product1.getCategory().getCategoryId());
    }

    @Test
    public void getProductById_thenSuccess() throws DataNotFoundException {
        Product product = productService.getProductById(1l);

        assertEquals(1, product.getProductId());
    }
}
