package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {
    private long productId;

    private String pname;

    private String categoryName;

    private String description;

    private Float rating;

    private long price;
}
