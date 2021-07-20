package com.nashtech.ecommerceapi.controller;

import com.nashtech.ecommerceapi.dto.CategoryDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {
    @Autowired
    CategoryController categoryController;

    @Test
    @WithMockUser
    public void findCategoryByName_thenSuccess() {
        CategoryDTO categoryDTO = (CategoryDTO) categoryController.findCategoryByName("mobile").getBody().getData();
        assertNotNull(categoryDTO);
        assertEquals("mobile", categoryDTO.getCname());
    }
}
