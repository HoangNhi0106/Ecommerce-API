package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Image;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.repository.RatingRepository;
import com.nashtech.ecommerceapi.service.ImageService;
import com.nashtech.ecommerceapi.service.ProductService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RatingService ratingService;

    @Autowired
    private ImageService imageService;

    public List<Product> getAllProducts()  {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            return null;
        else
            return products;
    }

    public Product getProductById(Long productId) throws DataNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent())
            return optionalProduct.get();
        else
            throw new DataNotFoundException(ErrorCode.ERROR_PRODUCT_NOT_FOUND);
    }

    public List<Product> getProductByCategory(Category category) {
        List<Product> products = productRepository.findAllByCategory(category);
        return products;
    }

    public Product addProduct(Product product) throws CreateDataFailException {
        product.setCreatedIn(LocalDateTime.now());
        Product saveProduct = productRepository.save(product);
        if (saveProduct != null)
            return saveProduct;
        else
            throw new CreateDataFailException(ErrorCode.ERROR_PRODUCT_NOT_SAVED);
    }

    public void deleteProduct(Long productId) throws DeleteDataFailException {
        try {
            Product product = getProductById(productId);
            List<Rating> ratings = ratingService.getRatingByProduct(product);
            if (ratings != null)
                for (Rating rating : ratings)
                    ratingService.deleteRating(rating.getRatingId());
            productRepository.deleteById(productId);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERROR_PRODUCT_NOT_DELETED);
        }

    }

    public void updateProduct(Product product) throws UpdateDataFailException {
        try {
            Product currentProduct = getProductById(product.getProductId());
            currentProduct.setPname(product.getPname());
            currentProduct.setCategory(product.getCategory());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setAmount(product.getAmount());
            currentProduct.setUpdatedIn(LocalDateTime.now());
            currentProduct.setImage(product.getImage());
            currentProduct.setRating(calculateRatingStar(currentProduct));
            productRepository.save(currentProduct);
        } catch (Exception e) {
            throw new UpdateDataFailException(ErrorCode.ERROR_PRODUCT_NOT_UPDATED);
        }
    }

    @Override
    public Float calculateRatingStar(Product product) throws DataNotFoundException {
        List<Rating> ratings = ratingService.getRatingByProduct(product);
        if (ratings != null) {
            Float star = (float) 0;
            for (Rating rating : ratings) {
                star += rating.getStar();
            }
            return star / ratings.size();
        } else {
            return (float) 0;
        }
    }

    public List<Product> getAllSortById() {
        List<Product> products = productRepository.findByOrderByProductIdAsc();
        if (products.isEmpty())
            return null;
        else return products;
    }

    public List<Product> getByNameContainting(String name) {
        List<Product> products = productRepository.findByPnameContainingIgnoreCase(name);
        if (products.isEmpty())
            return null;
        else
            return products;
    }
}
