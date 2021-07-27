package com.nashtech.ecommerceapi.dto;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTOUpdate {
    @NotNull
    private long categoryId;

    @NotNull
    private String cname;

    private String description;
}
