package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProductDTOUpdate {
    @NotNull
    private long productId;

    @NotNull
    private String pname;

    @NotNull
    private String categoryName;

    private String image;

    @Min(value = 0)
    private int amount;

    @Min(value = 0)
    private long price;

    private String description;

    private String brandName;

    private String volume;

    private String madeIn;

    private String skinType;
}
