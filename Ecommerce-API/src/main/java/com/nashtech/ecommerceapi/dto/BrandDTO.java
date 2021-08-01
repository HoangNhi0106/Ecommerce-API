package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class BrandDTO {
    private long brandId;

    private String bname;

    private String madeIn;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;
}
