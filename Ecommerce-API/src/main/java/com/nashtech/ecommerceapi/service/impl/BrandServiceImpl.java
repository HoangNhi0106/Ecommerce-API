package com.nashtech.ecommerceapi.service.impl;

import com.nashtech.ecommerceapi.constant.ErrorCode;
import com.nashtech.ecommerceapi.entity.Brand;
import com.nashtech.ecommerceapi.entity.Product;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;
import com.nashtech.ecommerceapi.repository.BrandRepository;
import com.nashtech.ecommerceapi.repository.ProductRepository;
import com.nashtech.ecommerceapi.service.BrandService;
import com.nashtech.ecommerceapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private ProductService productService;

    public List<Brand> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        if (brands.isEmpty())
            return null;
        else return brands;
    }

    public Brand getBrandById(Long id) {
        Optional<Brand> brand = brandRepository.findById(id);
        return brand.orElse(null);
    }

    public Brand getBrandByBname(String bname) {
        Optional<Brand> brand = brandRepository.findByBname(bname);
        return brand.orElse(null);
    }

    public Brand addBrand(Brand brand) throws CreateDataFailException {
        try {
            brand.setCreatedIn(LocalDateTime.now());
            return brandRepository.save(brand);
        } catch (Exception ex) {
            throw new CreateDataFailException(ErrorCode.ERROR_BRAND_NOT_SAVED);
        }
    }

    public void updateBrand(Brand brand) throws UpdateDataFailException {
        try {
            Brand currentBrand = getBrandById(brand.getBrandId());
            currentBrand.setMadeIn(brand.getMadeIn());
            currentBrand.setUpdatedIn(LocalDateTime.now());
            brandRepository.save(currentBrand);
        } catch (Exception ex) {
            throw new UpdateDataFailException(ErrorCode.ERROR_BRAND_NOT_UPDATED);
        }
    }

    public void deleteBrand(Long id) throws DeleteDataFailException {
        try {
            Brand brand = getBrandById(id);
            //delete all products
            List<Product> products = productService.getProductByBrand(brand);
            if (products != null) {
                for (Product product : products)
                    productService.deleteProduct(product.getProductId());
            }
            //delete brand
            brandRepository.deleteById(id);
        } catch (Exception e) {
            throw new DeleteDataFailException(ErrorCode.ERROR_BRAND_NOT_DELETED);
        }
    }
}
