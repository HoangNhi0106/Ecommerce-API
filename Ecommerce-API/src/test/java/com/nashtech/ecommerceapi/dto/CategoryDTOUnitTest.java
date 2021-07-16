package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryDTOUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertProductEntityToPostDto_thenCorrect() {
        Category category = new Category();
        category.setCategoryId(1);
        category.setCname("xyz");

        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);

        assertEquals(category.getCategoryId(), categoryDTO.getCategoryId());
        assertEquals(category.getCname(), categoryDTO.getCname());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setCategoryId(1);
        categoryDTO.setCname("xyz");

        Category category = modelMapper.map(categoryDTO, Category.class);

        assertEquals(category.getCategoryId(), categoryDTO.getCategoryId());
        assertEquals(category.getCname(), categoryDTO.getCname());
    }
}
