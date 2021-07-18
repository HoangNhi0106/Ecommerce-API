package com.nashtech.ecommerceapi.repository;

import com.nashtech.ecommerceapi.entity.Account;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByProduct(Product product);
    List<Rating> findAllByAccount(Account account);
}
