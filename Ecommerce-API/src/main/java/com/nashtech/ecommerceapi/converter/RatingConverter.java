package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.RatingDTO;
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
        ratingDTO.setProductId(rating.getProduct().getProduct_id());
        ratingDTO.setAccountId(rating.getAccount().getAccountId());
        return ratingDTO;
    }

    public Rating convertToEntity(RatingDTO ratingDTO) throws ParseException {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        rating.setAccount(accountRepository.getById(ratingDTO.getAccountId()));
        rating.setProduct(productRepository.getById(ratingDTO.getProductId()));
        return rating;
    }
}
