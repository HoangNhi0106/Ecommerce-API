package com.nashtech.ecommerceapi.repository;

import com.nashtech.ecommerceapi.entity.Category;
import com.nashtech.ecommerceapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByOrderByProductIdAsc();
    List<Product> findAllByCategory(Category category);

    List<Product> findByPnameContainingIgnoreCase(String name);
}
