package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Rating;

import java.util.List;

public interface RatingService {
    public List<Rating> getAllRating();

    public Rating getRatingById(Long rating_id);

    public Rating addRating(Rating rating);

    public void deleteRating(Long rating_id);
}
