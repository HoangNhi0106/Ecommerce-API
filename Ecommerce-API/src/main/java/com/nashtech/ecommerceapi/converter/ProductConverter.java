package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.*;
import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.service.CategoryService;
import com.nashtech.ecommerceapi.service.ImageService;
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

    @Autowired
    private ImageService imageService;

    @Autowired
    private ImageConverter imageConverter;

    public ProductDTO convertToDto(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        productDTO.setProductId(product.getProductId());
        productDTO.setCategoryName(product.getCategory().getCname());
        Image image = product.getImage();
        if (image != null) {
            ImageDTO imageDTO = imageConverter.convertToDto(image);
            productDTO.setImage(imageDTO.getUrl());
        }
        return productDTO;
    }

    public Product convertToEntity(ProductDTO productDTO) throws DataNotFoundException {
        Product product = modelMapper.map(productDTO, Product.class);
        product.setCategory(categoryService.getCategoryByName(productDTO.getCategoryName()));
        return product;
    }

    public ProductDTOItem convertToDtoItem(Product product) {
        ProductDTOItem productDTOItem = modelMapper.map(product, ProductDTOItem.class);
        productDTOItem.setProductId(product.getProductId());
        Image image = product.getImage();
        if (image != null) {
            ImageDTO imageDTO = imageConverter.convertToDto(image);
            productDTOItem.setImage(imageDTO.getUrl());
        }
        return productDTOItem;
    }

    /*public ProductDTOUpdate convertToDtoUpdate(Product product) {
        ProductDTOUpdate productDTOUpdate = modelMapper.map(product, ProductDTOUpdate.class);
        productDTOUpdate.setProductId(product.getProductId());
        productDTOUpdate.setCategoryName(product.getCategory().getCname());
        return productDTOUpdate;
    }

    public ProductDTOCreate convertToDtoCreate(Product product) {
        ProductDTOCreate productDTOCreate = modelMapper.map(product, ProductDTOCreate.class);
        productDTOCreate.setCategoryName(product.getCategory().getCname());
        return productDTOCreate;
    }*/

    public Product convertToEntityCreate(ProductDTOCreate productDTOCreate) throws DataNotFoundException {
        Product product = modelMapper.map(productDTOCreate, Product.class);
        product.setCategory(categoryService.getCategoryByName(productDTOCreate.getCategoryName()));
        if (productDTOCreate.getImage() != null)
            product.setImage(imageService.getImageById(productDTOCreate.getImage()));
        return product;
    }

    public Product convertToEntityUpdate(ProductDTOUpdate productDTOUpdate) throws DataNotFoundException {
        Product product = modelMapper.map(productDTOUpdate, Product.class);
        product.setProductId(productDTOUpdate.getProductId());
        product.setCategory(categoryService.getCategoryByName(productDTOUpdate.getCategoryName()));
        if (productDTOUpdate.getImage() != null)
            product.setImage(imageService.getImageById(productDTOUpdate.getImage()));
        return product;
    }
}
