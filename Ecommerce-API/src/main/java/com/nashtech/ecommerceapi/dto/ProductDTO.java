package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private long productId;

    private String pname;

    private String categoryName;

    private int sold;

    private long price;

    private Float rating;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;

    private String description;
}
