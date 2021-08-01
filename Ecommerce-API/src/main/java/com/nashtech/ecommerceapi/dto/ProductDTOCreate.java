package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;

@Getter
@Setter
public class ProductDTOCreate {
    @NotNull
    private String pname;

    @NotNull
    private String categoryName;

    @Min(value = 0)
    private int amount;

    @Min(value = 0)
    private long price;

    private String image;

    private String description;

    @NotNull
    private String brandName;

    private String volume;

    private String madeIn;

    private String skinType;
}
