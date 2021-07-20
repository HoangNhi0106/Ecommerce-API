package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.dto.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductControllerTest {
    @Autowired
    ProductController productController;

    @Test
    @WithMockUser
    public void findProductByIdTest_thenSuccess()
    {
        ProductDTO product = (ProductDTO) productController.findProduct(1l).getBody().getData();
        assertNotNull(product);
        assertEquals(1, product.getProductId());
    }
}
