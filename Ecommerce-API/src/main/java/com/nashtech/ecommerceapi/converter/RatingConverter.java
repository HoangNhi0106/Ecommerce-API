package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.RatingDTO;
import com.nashtech.ecommerceapi.dto.RatingDTOReview;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.repository.AccountRepository;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class RatingConverter {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    public RatingDTO convertToDto(Rating rating) {
        RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
        ratingDTO.setRatingId(rating.getRatingId());
        ratingDTO.setProductId(rating.getProduct().getProductId());
        ratingDTO.setAccountId(rating.getAccount().getAccountId());
        return ratingDTO;
    }

    public Rating convertToEntity(RatingDTO ratingDTO) throws ParseException {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        rating.setAccount(accountRepository.getById(ratingDTO.getAccountId()));
        rating.setProduct(productRepository.getById(ratingDTO.getProductId()));
        return rating;
    }

    public RatingDTOReview convertToDtoReview(Rating rating) {
        RatingDTOReview ratingDTOReview = modelMapper.map(rating, RatingDTOReview.class);
        ratingDTOReview.setRatingId(rating.getRatingId());
        ratingDTOReview.setAccountUsername(rating.getAccount().getUsername());
        return ratingDTOReview;
    }
}
