package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
public class ProductDTOItem {
    private long productId;

    private String pname;

    @Min(value = 0, message = "The star must be a positive ingeter")
    @Max(value = 5, message = "Maximum star is 5")
    private Float rating;

    private long price;

    private String image;
}
