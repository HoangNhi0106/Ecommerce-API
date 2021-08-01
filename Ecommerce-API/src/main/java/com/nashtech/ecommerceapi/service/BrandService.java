package com.nashtech.ecommerceapi.service;

import com.nashtech.ecommerceapi.entity.Brand;
import com.nashtech.ecommerceapi.exception.CreateDataFailException;
import com.nashtech.ecommerceapi.exception.DeleteDataFailException;
import com.nashtech.ecommerceapi.exception.UpdateDataFailException;

import java.util.List;

public interface BrandService {
    public List<Brand> getAllBrands();

    public Brand getBrandById(Long id);

    public Brand getBrandByBname(String bname);

    public Brand addBrand(Brand brand) throws CreateDataFailException;

    public void updateBrand(Brand brand) throws UpdateDataFailException;

    public void deleteBrand(Long id) throws DeleteDataFailException;
}
