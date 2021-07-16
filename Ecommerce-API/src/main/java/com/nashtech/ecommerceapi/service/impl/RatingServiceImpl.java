package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.repository.RatingRepository;
import com.nashtech.ecommerceapi.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;

    public List<Rating> getAllRating() {
        return ratingRepository.findAll();
    }

    public Rating getRatingById(Long ratingId) {
        return ratingRepository.getById(ratingId);
    }

    public Rating addRating(Rating rating) {
        return ratingRepository.save(rating);
    }

    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
