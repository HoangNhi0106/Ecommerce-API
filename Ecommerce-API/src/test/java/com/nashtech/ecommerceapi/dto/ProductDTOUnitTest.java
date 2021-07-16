package com.nashtech.ecommerceapi.dto;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductDTOUnitTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void whenConvertProductEntityToPostDto_thenCorrect() {
        Product product = new Product();
        Category category = new Category();
        category.setCategoryId(1);
        category.setCname("xyz");
        product.setProduct_id(1);
        product.setPname("abc");
        product.setCategory(category);
        product.setAmount(50);
        product.setPrice(1000000);
        product.setDescription("hello");

        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setCategoryName(product.getCategory().getCname());

        assertEquals(product.getProduct_id(), productDTO.getProductId());
        assertEquals(product.getPname(), productDTO.getPname());
        assertEquals(product.getCategory().getCname(), productDTO.getCategoryName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getDescription(), productDTO.getDescription());
    }

    @Test
    public void whenConvertPostDtoToPostEntity_thenCorrect() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductId(1);
        productDTO.setPname("abc");
        productDTO.setCategoryName("xyz");
        productDTO.setPrice(100000);
        productDTO.setDescription("hello");

        Product product = modelMapper.map(productDTO, Product.class);
        Category category = new Category();
        category.setCategoryId(1);
        category.setCname(productDTO.getCategoryName());
        product.setCategory(category);

        assertEquals(product.getProduct_id(), productDTO.getProductId());
        assertEquals(product.getPname(), productDTO.getPname());
        assertEquals(product.getCategory().getCname(), productDTO.getCategoryName());
        assertEquals(product.getPrice(), productDTO.getPrice());
        assertEquals(product.getDescription(), productDTO.getDescription());
    }
}
