package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class ProductConverter {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    public ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProductId(product.getProductId());
        productDTO.setCategoryName(product.getCategory().getCname());
        return productDTO;
    }

    public Product convertToEntity(ProductDTO productDTO) throws DataNotFoundException {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(categoryService.getCategoryByName(productDTO.getCategoryName()));
        return product;
    }
}
