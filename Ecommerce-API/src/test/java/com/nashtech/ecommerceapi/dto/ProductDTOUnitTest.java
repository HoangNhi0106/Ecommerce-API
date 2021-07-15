package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@TestConfiguration
public class ProductDTOUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertProductEntityToPostDto_thenCorrect() {
        Product product = new Product();
        Category category = new Category();
        category.setCategory_id(1);
        category.setName("xyz");
        product.setProduct_id(1);
        product.setName("abc");
        product.setCategory(category);
        product.setAmount(50);
        product.setPrice(1000000);
        product.setDescription("hello");

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setCategory_name(product.getCategory().getName());

        assertEquals(product.getProduct_id(), productDTO.getProduct_id());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getCategory().getName(), productDTO.getCategory_name());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getDescription(), productDTO.getDescription());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct_id(1);
        productDTO.setName("abc");
        productDTO.setCategory_name("xyz");
        productDTO.setPrice(100000);
        productDTO.setDescription("hello");

        Product product = modelMapper.map(productDTO, Product.class);
        Category category = new Category();
        category.setCategory_id(1);
        category.setName(productDTO.getCategory_name());
        product.setCategory(category);

        assertEquals(product.getProduct_id(), productDTO.getProduct_id());
        assertEquals(product.getName(), productDTO.getName());
        assertEquals(product.getCategory().getName(), productDTO.getCategory_name());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getDescription(), productDTO.getDescription());
    }
}
