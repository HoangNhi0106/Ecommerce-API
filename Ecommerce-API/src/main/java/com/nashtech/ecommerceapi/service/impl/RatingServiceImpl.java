package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.repository.RatingRepository;
import com.nashtech.ecommerceapi.service.ProductService;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private ProductService productService;

    public List<Rating> getAllRatings(){
        List<Rating> ratings = ratingRepository.findAll();
        if (ratings.isEmpty())
            return null;
        return ratings;
    }

    public Rating getRatingById(Long ratingId) throws DataNotFoundException {
        Optional<Rating> optionalRating = ratingRepository.findById(ratingId);
        if (optionalRating.isPresent())
            return optionalRating.get();
        else
            throw new DataNotFoundException(ErrorCode.ERROR_RATING_NOT_FOUND);
    }

    public Rating addRating(Rating rating) throws CreateDataFailException, UpdateDataFailException {
        //save rating
        rating.setDate(LocalDateTime.now());
        Rating saveRating = ratingRepository.save(rating);

        if (saveRating == null)
            throw new CreateDataFailException(ErrorCode.ERROR_RATING_NOT_SAVED);
        else {
            //update product rating
            Product product = rating.getProduct();
            productService.updateProduct(product);
            return saveRating;
        }
    }

    public void deleteRating(Long ratingId) throws DeleteDataFailException {
        try {
            //update product rating
            Rating rating = getRatingById(ratingId);
            Product product = productService.getProductById(rating.getProduct().getProductId());
            productService.updateProduct(product);
            //delete
            ratingRepository.deleteById(ratingId);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERROR_RATING_NOT_DELETED);
        }

    }

    public List<Rating> getRatingByProduct(Product product) {
        List<Rating> ratings = ratingRepository.findAllByProduct(product);
        if (ratings.isEmpty())
            return null;
        return ratings;
    }

    public List<Rating> getRatingByAccount(Account account) {
        List<Rating> ratings = ratingRepository.findAllByAccount(account);
        if (ratings.isEmpty())
            return null;
        return ratings;
    }
}
