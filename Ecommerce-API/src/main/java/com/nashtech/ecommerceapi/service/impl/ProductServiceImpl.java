package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.repository.RatingRepository;
import com.nashtech.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RatingRepository ratingRepository;

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void setRatingRepository(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Long product_id) {
        return productRepository.findById(product_id).get();
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long product_id) {
        productRepository.deleteById(product_id);
    }

    public void updateProduct(Product product) {
        productRepository.save(product);
    }

    @Override
    public Float calculateRatingStar(Product product) {
        List<Rating> ratings = ratingRepository.findAllByProduct(product);
        if (ratings.size() != 0) {
            Float star = (float) 0;
            for (Rating rating : ratings) {
                star += rating.getStar();
            }
            return star / ratings.size();
        } else {
            return (float) 0;
        }
    }

}
