package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BrandDTOUpdate {
    @NotNull
    private long brandId;
    @NotNull
    private String madeIn;
}
