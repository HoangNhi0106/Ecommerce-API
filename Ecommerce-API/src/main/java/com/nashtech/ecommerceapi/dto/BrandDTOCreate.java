package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandDTOCreate {
    @NotNull
    private String bname;

    @NotNull
    private String madeIn;
}
