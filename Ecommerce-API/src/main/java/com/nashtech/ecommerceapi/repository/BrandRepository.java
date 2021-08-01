package com.nashtech.ecommerceapi.repository;

import com.nashtech.ecommerceapi.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Optional<Brand> findByBname(String bname);
}
