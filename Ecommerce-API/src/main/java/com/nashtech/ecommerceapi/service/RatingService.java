package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DataNotFoundException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;

import java.util.List;

public interface RatingService {
    public List<Rating> getAllRatings();

    public Rating getRatingById(Long ratingId) throws DataNotFoundException;

    public Rating addRating(Rating rating) throws CreateDataFailException, DataNotFoundException, UpdateDataFailException;

    public void deleteRating(Long ratingId) throws DeleteDataFailException;

    public List<Rating> getRatingByProduct(Product product) throws DataNotFoundException;

    public List<Rating> getRatingByAccount(Account account) throws DataNotFoundException;
}
