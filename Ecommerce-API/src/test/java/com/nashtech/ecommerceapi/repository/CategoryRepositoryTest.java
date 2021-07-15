package com.nashtech.ecommerceapi.repository;

import com.nashtech.ecommerceapi.entity.Category;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findCategoryByName_thenSuccess() {
        Category category = new Category();
        category.setCategory_id(1);
        category.setName("xyz");
        assertNotNull(categoryRepository.save(category));

        Category categoryTest = categoryRepository.findCategoryByName(category.getName());

        assertNotNull(categoryTest);

        assertEquals(category.getCategory_id(), categoryTest.getCategory_id());
        assertEquals(category.getName(), categoryTest.getName());
    }
}
