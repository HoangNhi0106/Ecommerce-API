package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@TestConfiguration
public class CategoryDTOUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertProductEntityToPostDto_thenCorrect() {
        Category category = new Category();
        category.setCategory_id(1);
        category.setName("xyz");

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        assertEquals(category.getCategory_id(), categoryDTO.getCategory_id());
        assertEquals(category.getName(), categoryDTO.getName());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategory_id(1);
        categoryDTO.setName("xyz");

        Category category = modelMapper.map(categoryDTO, Category.class);

        assertEquals(category.getCategory_id(), categoryDTO.getCategory_id());
        assertEquals(category.getName(), categoryDTO.getName());
    }
}
