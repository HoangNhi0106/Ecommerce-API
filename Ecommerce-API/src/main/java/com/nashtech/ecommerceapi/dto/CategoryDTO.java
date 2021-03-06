package com.nashtech.ecommerceapi.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {
    private long categoryId;

    private String cname;

    private String description;

    private LocalDateTime createdIn;

    private LocalDateTime updatedIn;
}
