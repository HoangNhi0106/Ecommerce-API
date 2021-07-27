package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.RatingDTOPost;
import com.nashtech.ecommerceapi.dto.RatingDTOReview;
import com.nashtech.ecommerceapi.dto.RatingDTOShow;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.service.AccountService;
import com.nashtech.ecommerceapi.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class RatingConverter {
    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ModelMapper modelMapper;

    public RatingDTOPost convertToDto(Rating rating) {
        RatingDTOPost ratingDTOPost = modelMapper.map(rating, RatingDTOPost.class);
        ratingDTOPost.setRatingId(rating.getRatingId());
        ratingDTOPost.setProductId(rating.getProduct().getProductId());
        ratingDTOPost.setAccountId(rating.getAccount().getAccountId());
        return ratingDTOPost;
    }

    public Rating convertToEntity(RatingDTOPost ratingDTOPost) throws ParseException, DataNotFoundException {
        Rating rating = modelMapper.map(ratingDTOPost, Rating.class);
        rating.setAccount(accountService.getAccountById(ratingDTOPost.getAccountId()));
        rating.setProduct(productService.getProductById(ratingDTOPost.getProductId()));
        return rating;
    }

    public RatingDTOReview convertToDtoReview(Rating rating) {
        RatingDTOReview ratingDTOReview = modelMapper.map(rating, RatingDTOReview.class);
        ratingDTOReview.setRatingId(rating.getRatingId());
        ratingDTOReview.setAccountUsername(rating.getAccount().getUsername());
        return ratingDTOReview;
    }

    public RatingDTOShow convertToDtoShow(Rating rating) {
        RatingDTOShow ratingDTOShow = modelMapper.map(rating, RatingDTOShow.class);
        ratingDTOShow.setRatingId(rating.getRatingId());
        ratingDTOShow.setProductId(rating.getProduct().getProductId());
        ratingDTOShow.setPname(rating.getProduct().getPname());
        ratingDTOShow.setUsername(rating.getAccount().getUsername());
        return ratingDTOShow;
    }
}
