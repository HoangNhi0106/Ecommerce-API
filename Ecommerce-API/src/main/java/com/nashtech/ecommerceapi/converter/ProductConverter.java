package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.ProductDTO;
import com.nashtech.ecommerceapi.dto.RatingDTO;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
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
        productDTO.setCategory_name(product.getCategory().getName());
        return productDTO;
    }

    public Product convertToEntity(ProductDTO productDTO) throws ParseException {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(categoryService.getCategoryByName(productDTO.getCategory_name()));
        return product;
    }
}