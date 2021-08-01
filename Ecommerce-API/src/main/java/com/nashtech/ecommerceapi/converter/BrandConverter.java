package com.nashtech.ecommerceapi.converter;

import com.nashtech.ecommerceapi.dto.*;
import com.nashtech.ecommerceapi.entity.Brand;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class BrandConverter {
    @Autowired
    private ModelMapper modelMapper;

    public BrandDTO convertToDto(Brand brand) {
        BrandDTO brandDTO = modelMapper.map(brand, BrandDTO.class);
        brandDTO.setBrandId(brand.getBrandId());
        return brandDTO;
    }

    public Brand convertToEntity(BrandDTO brandDTO) throws ParseException {
        Brand brand = modelMapper.map(brandDTO, Brand.class);
        return brand;
    }

    public Brand convertToEntityCreate(BrandDTOCreate brandDTOCreate) throws ParseException {
        Brand brand = modelMapper.map(brandDTOCreate, Brand.class);
        return brand;
    }

    public Brand convertToEntityUpdate(BrandDTOUpdate brandDTOUpdate) throws ParseException {
        Brand brand = modelMapper.map(brandDTOUpdate, Brand.class);
        return brand;
    }
}
