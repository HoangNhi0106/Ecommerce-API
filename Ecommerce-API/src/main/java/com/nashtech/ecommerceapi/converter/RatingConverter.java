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
        ratingDTO.setProduct_id(rating.getProduct().getProduct_id());
        ratingDTO.setAccount_id(rating.getAccount().getAccount_id());
        return ratingDTO;
    }

    public Rating convertToEntity(RatingDTO ratingDTO) throws ParseException {
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        rating.setAccount(accountRepository.getById(ratingDTO.getAccount_id()));
        rating.setProduct(productRepository.getById(ratingDTO.getProduct_id()));
        return rating;
    }
}
