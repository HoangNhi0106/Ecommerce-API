package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTO {
    private long productId;

    private String pname;

    private String categoryName;

    @Min(value = 0)
    private int sold;

    @Min(value = 0)
    private long price;

    @Min(value = 0, message = "The star must be a positive ingeter")
    @Max(value = 5, message = "Maximum star is 5")
    private Float rating;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;

    private String description;
}
