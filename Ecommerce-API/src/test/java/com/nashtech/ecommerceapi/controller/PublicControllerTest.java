package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.dto.CategoryDTO;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PublicControllerTest {
    @Autowired
    private PublicController publicController;

    //Test
    @Test
    @WithMockUser
    public void findCategoryByName_Test() throws DataNotFoundException {
        CategoryDTO category = (CategoryDTO) publicController.findCategoryByName("toner").getBody().getData();
        Assertions.assertNotNull(category);
        Assertions.assertEquals("toner", category.getCname());
    }
}
